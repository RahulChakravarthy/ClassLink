package ca.uwaterloo.ca.classlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;

public class joinLectureGroupMenu extends AppCompatActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lecture_group_menu);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
