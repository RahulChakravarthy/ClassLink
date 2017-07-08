package app.classlink;

import android.os.Bundle;

import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class lectureRoom extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_lecture_room);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
