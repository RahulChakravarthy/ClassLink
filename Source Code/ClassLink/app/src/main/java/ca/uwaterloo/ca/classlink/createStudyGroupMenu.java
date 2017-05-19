package ca.uwaterloo.ca.classlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;

public class createStudyGroupMenu extends AppCompatActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_study_group_menu);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
