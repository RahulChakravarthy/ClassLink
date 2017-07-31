package app.classlink;

import android.content.Intent;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.groups.study.studyGroup;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

import app.classlink.helperClasses.recyclerAdapters.groupAdapter;

public class mainMenu extends baseActivity implements activityParameters {

    //Setting up ImageViews that become buttons
    private ImageView studyGroup;
    private ImageView lecture;
    private ImageView settings;
    private ImageView profile;
    private ImageView notifications;
    private LinkedList<ImageView> imageViews;

    private RecyclerView favourites;
    private List<app.classlink.backend.groups.study.studyGroup> groupList = new ArrayList<>();
    private groupAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //User authentication
        userAuth = FirebaseAuth.getInstance();

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_main_menu);
        viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
        buttonSetup();

        exampleGroups();

    }

    /**
     * @Method exampleGroups: set up some example groups to populate the RecyclerView
     */
    public void exampleGroups() {
        studyGroup group = new studyGroup(GROUP_TYPE.STUDY_GROUP, "1", "group1", "test group 1");
        groupList.add(group);

        group = new studyGroup(GROUP_TYPE.STUDY_GROUP, "2", "group2", "test group 2");
        groupList.add(group);

        group = new studyGroup(GROUP_TYPE.STUDY_GROUP, "3", "group3", "test group 3");
        groupList.add(group);

        group = new studyGroup(GROUP_TYPE.STUDY_GROUP, "99", "Add a new favourite", "Click here");
        groupList.add(group);
    }

    /**
     * @Method layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
       this.activityLayout.setBackgroundResource(R.drawable.bg);

        imageViews = new LinkedList<>();

        studyGroup = (ImageView) findViewById(R.id.buttonStudyGroup);
        lecture = (ImageView) findViewById(R.id.buttonLecture);
        settings = (ImageView) findViewById(R.id.buttonSettings);
        profile = (ImageView) findViewById(R.id.buttonProfile);
        notifications = (ImageView) findViewById(R.id.buttonMail);

        imageViews.add(studyGroup);
        imageViews.add(lecture);
        imageViews.add(settings);
        imageViews.add(profile);
        imageViews.add(notifications);

        for (ImageView i : imageViews) {
            viewHelperClass.imageToButton(i);
        }

        favourites = (RecyclerView) findViewById(R.id.recyclerFavourites);
        gAdapter = new groupAdapter(groupList);
        RecyclerView.LayoutManager gLayoutManager = new LinearLayoutManager(getApplicationContext());
        favourites.setLayoutManager(gLayoutManager);
        favourites.setItemAnimator(new DefaultItemAnimator());
        favourites.setAdapter(gAdapter);
    }

    /**
     * @Method buttonSetup : Sets up all button listeners for the activity
     */
    public void buttonSetup() {

        studyGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, studyRoom.class));
            }
        });

        lecture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, lectureJoin.class));
            }
        });

       settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, app.classlink.settings.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, app.classlink.userProfile.class));
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, app.classlink.notifications.class));
            }
        });


        favourites.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int actions;       //onInterceptTouchEvent fires once onPress and once again onRelease so this is a quick fix to only fire onClick once for these two actions... also sometimes on scroll it will increment as well
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                actions++;
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && actions >= 2) {
                    onClick(child, rv.getChildPosition(child));
                    actions = 0;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            public void onClick(View view, int position) {
                studyGroup studyGroup = groupList.get(position);
                Log.d("Testing onClick", "description: " + studyGroup.getGroupDescription());

                if(Objects.equals(studyGroup.getGroupId(), "99")) {
                    studyGroup newFavourite = new studyGroup(GROUP_TYPE.STUDY_GROUP, "5", "new favourite", "added a new favourite");
                    groupList.add(newFavourite);
                    gAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}

/*
        studyGroup = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(studyGroup, R.drawable.button_study_group_grey, -265, 350, 0.475f, 0.475f, true);

        lecture = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(lecture, R.drawable.button_lecture_grey, 265, 350, 0.475f, 0.475f, true);

        /*settings = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(settings, R.drawable.button_wide, 0, 1090, 0.969f, 1.0f, true);

        profile = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(profile, R.drawable.circle_grey, -145, -150, 0.55f, 0.55f, true);

        /*notifications = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(notifications, R.drawable.field_notification, 260, -150, 0.45f, 0.6f, false);*/
