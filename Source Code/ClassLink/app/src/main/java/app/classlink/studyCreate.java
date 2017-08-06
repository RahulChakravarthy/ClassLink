package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.backend.core.baseActivity;

public class studyCreate extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_create);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_study_create);

        layoutSetup();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(studyCreate.this, login.class));
        }
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
