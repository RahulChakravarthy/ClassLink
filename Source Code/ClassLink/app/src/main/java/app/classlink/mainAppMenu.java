package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class mainAppMenu extends baseActivity implements activityParameters {

    //Setting up ImageViews that become buttons
    private ImageView studyGroup;
    private ImageView lecture;
    private ImageView settings;
    private ImageView profile;
    private ImageView notifications; //this one will likely change, so consider it a placeholder for now

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_menu);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_main_app_menu);
        viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
        buttonSetup();

    }

    /**
     * @Method layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundColor(Color.parseColor("#3d80b0"));

        studyGroup = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(studyGroup, R.drawable.button_study_group_up, -265, 350, 0.475f, 0.475f, true);

        lecture = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(lecture, R.drawable.button_lecture_up, 265, 350, 0.475f, 0.475f, true);

        settings = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(settings, R.drawable.button_wide, 0, 1090, 0.969f, 1.0f, true);

        profile = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(profile, R.drawable.button_profile, -145, -150, 0.55f, 0.55f, true);

        notifications = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(notifications, R.drawable.field_notification, 260, -150, 0.45f, 0.6f, false);

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


    }
}