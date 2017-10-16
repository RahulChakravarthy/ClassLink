package app.classlink.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
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

    //MISC
    private Timer groupQueryTimer;
    private final int PINGSPEED = 500;
    private int attempts = 10;


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
            startActivity(new Intent(mainMenu.this, login.class));
        }
    }

    @Override
    public void onBackPressed(){
        //Do nothing
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();
        this.lectureGroupDAO = new LectureGroupDAO();

        //Create a timer that continuously pings server for lecture group data, if it takes too long, then throw internet speed error and warn user
        this.groupQueryTimer = new Timer();
        this.groupQueryTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    lectureGroupDAO.setCacheListener(userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getSchool().toString());
                    groupQueryTimer.cancel();
                } catch (NullPointerException e){
                    attempts--;
                }
                if (attempts < 0){
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "Your internet speed is slow, user groups and data might not load properly", Toast.LENGTH_SHORT).show();
                    groupQueryTimer.cancel();
                }
            }
        }, 0,PINGSPEED);
    }

    /**
     * @Method layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
       this.activityLayout.setBackgroundResource(R.drawable.bg);

        this.imageViews = new LinkedList<>();

        this.studyGroup = (ImageView) findViewById(R.id.buttonStudyGroup);
        this.lecture = (ImageView) findViewById(R.id.buttonLecture);
        this.settings = (ImageView) findViewById(R.id.buttonSettings);
        this.profile = (ImageView) findViewById(R.id.buttonProfile);
        this.notifications = (ImageView) findViewById(R.id.buttonMail);

        this.imageViews.add(studyGroup);
        this.imageViews.add(lecture);
        this.imageViews.add(settings);
        this.imageViews.add(profile);
        this.imageViews.add(notifications);

        for (ImageView i : imageViews) {
            this.viewHelperClass.imageToButton(i);
        }

    }

    /**
     * @Method buttonSetup : Sets up all button listeners for the activity
     */
    public void buttonSetup() {

        this.studyGroup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, studyRoom.class));
            }
        });
        lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()) != null) {
                    Intent intent = new Intent(mainMenu.this, lectureJoin.class);
                    intent.putExtra("user", userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail())); //add the current user object in to access data in the next activity rapidly
                    intent.putExtra("allLectureGroups", lectureGroupDAO.getAllLectureGroups());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please wait for user data to load", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainMenu.this, settings.class));
            }
        });

        this.profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()) != null)){
                    Intent intent = new Intent(mainMenu.this, userProfile.class);
                    intent.putExtra("user", userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail())); //add the current user object in to access data in the next activity rapidly
                    intent.putExtra("allLectureGroups", lectureGroupDAO.getAllLectureGroups());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please wait for user data to load", Toast.LENGTH_SHORT).show();
                }

            }
        });

        this.notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature not implemented yet", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(mainMenu.this, notifications.class));
            }
        });
    }
}