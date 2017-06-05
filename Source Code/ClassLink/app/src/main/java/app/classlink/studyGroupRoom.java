package app.classlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class studyGroupRoom extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_group_room);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
