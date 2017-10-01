package app.classlink.frontend;

import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.helperClasses.activityParameters;

public class studyJoin extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_join);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_study_join);

        layoutSetup();
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {

    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundResource(R.drawable.bg);
    }
}
