package app.classlink;

import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.backend.core.baseActivity;

public class studyJoin extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_join);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_study_join);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundResource(R.drawable.bg);
    }

    @Override
    protected void setActivityDAOListeners() {

    }
}
