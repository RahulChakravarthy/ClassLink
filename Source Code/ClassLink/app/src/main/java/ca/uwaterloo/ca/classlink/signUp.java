package ca.uwaterloo.ca.classlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;

public class signUp extends AppCompatActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
