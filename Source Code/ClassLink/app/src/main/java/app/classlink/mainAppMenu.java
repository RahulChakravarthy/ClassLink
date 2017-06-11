package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

import app.classlink.backend.*;
import app.classlink.favouritesBar.groupAdapter;

public class mainAppMenu extends baseActivity implements activityParameters {

    //Setting up ImageViews that become buttons
    private ImageView studyGroup;
    private ImageView lecture;
    private ImageView settings;
    private ImageView profile;
    private ImageView notifications;
    private LinkedList<ImageView> imageViews;

    private RecyclerView favourites;
    private List<studyGroup> groupList = new ArrayList<>();
    private groupAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_menu);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_main_app_menu);
        viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext());

        layoutSetup();
        buttonSetup();

        exampleGroups();

    }

    /**
     * @Method exampleGroups: set up some example groups to populate the RecyclerView
     */
    public void exampleGroups() {
        studyGroup group = new studyGroup(GROUP_TYPE.STUDY_GROUP, 1, "group1", "test group 1");
        groupList.add(group);

        group = new studyGroup(GROUP_TYPE.STUDY_GROUP, 2, "group2", "test group 2");
        groupList.add(group);

        group = new studyGroup(GROUP_TYPE.STUDY_GROUP, 3, "group3", "test group 3");
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
                startActivity(new Intent(mainAppMenu.this, app.classlink.studyGroupMenu.class));
            }
        });

        lecture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainAppMenu.this, app.classlink.classLectureMenu.class));
            }
        });

       settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainAppMenu.this, app.classlink.settings.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainAppMenu.this, app.classlink.userProfile.class));
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
