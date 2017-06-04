package app.classlink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class settings extends AppCompatActivity implements activityParameters {

    protected RelativeLayout settingsLayout;
    protected app.classlink.helperClasses.viewHelperClass viewHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settingsLayout = (RelativeLayout) findViewById(R.id.activity_settings);
        viewHelperClass = new viewHelperClass(settingsLayout, getApplicationContext());

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.settingsLayout.setBackgroundColor(Color.parseColor("#3d80b0"));
    }
}
