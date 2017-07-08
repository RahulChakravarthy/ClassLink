package app.classlink;

import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class studyJoin extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_join);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_join_study_group_menu);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundResource(R.drawable.bg);
    }
}
