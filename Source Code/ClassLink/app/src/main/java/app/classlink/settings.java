package app.classlink;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class settings extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_settings);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext());

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundColor(Color.parseColor("#3d80b0"));
    }
}
