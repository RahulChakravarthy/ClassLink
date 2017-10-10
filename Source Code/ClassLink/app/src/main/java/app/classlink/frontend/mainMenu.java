package app.classlink.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.groups.study.studyGroup;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.recyclerAdapters.groupAdapter;
import app.classlink.helperClasses.viewHelperClass;

public class mainMenu extends baseActivity implements activityParameters {

    //Setting up ImageViews that become buttons
    private ImageView studyGroup;
    private ImageView lecture;
    private ImageView settings;
    private ImageView profile;
    private ImageView notifications;
    private LinkedList<ImageView> imageViews;

    //DAOs
    private userDAO userDAO;
    private LectureGroupDAO lectureGroupDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_main_menu);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
        buttonSetup();
        setActivityDAOListeners();
    }

    /**
     * @Method onResume : Checks authentication of current user and redirects them to login screen if authentication fails
     */
    @Override
    protected void onResume(){
        super.onResume();
        if (!retrieveUser()){
            Toast.makeText(getApplicationContext(), "Re-authentication needed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(mainMenu.this, login.class));
        }
    }

    @Override
    public void onBackPressed(){
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        final int DATA_QUERY_DELAY = 900;
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();
        //This allows time for the user to be queried and to access their school
        this.lectureGroupDAO = new LectureGroupDAO();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lectureGroupDAO.setCacheListener(userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getSchool().toString());
            }
        }, DATA_QUERY_DELAY);

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
                final int NEXT_ACTIVITY_DELAY = 300;
                //In order to give the DAO's enough time to query the information
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(mainMenu.this, lectureJoin.class);
                        intent.putExtra("user", userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail())); //add the current user object in to access data in the next activity rapidly
                        intent.putExtra("allLectureGroups", lectureGroupDAO.getAllLectureGroups());
                        startActivity(intent);
                    }
                },NEXT_ACTIVITY_DELAY);
            }
        });

       settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, settings.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainMenu.this, userProfile.class);
                intent.putExtra("user", userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail())); //add the current user object in to access data in the next activity rapidly
                intent.putExtra("allLectureGroups", lectureGroupDAO.getAllLectureGroups());
                startActivity(intent);
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature not implemented yet", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(mainMenu.this, notifications.class));
            }
        });
    }
}