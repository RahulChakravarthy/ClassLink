package ca.uwaterloo.ca.classlink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;
import ca.uwaterloo.ca.classlink.MiscHelperClasses.viewHelperClass;

public class mainAppMenu extends AppCompatActivity implements activityParameters {

    protected RelativeLayout mainAppMenuLayout;
    protected viewHelperClass viewHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_menu);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Settings
        this.mainAppMenuLayout = (RelativeLayout) findViewById(R.id.activity_main_app_menu);
        this.mainAppMenuLayout.setBackgroundColor(Color.GREEN);

        //Text Settings
        this.viewHelperClass = new viewHelperClass(this.mainAppMenuLayout, this.getApplicationContext());
        this.viewHelperClass.addText("THIS IS THE mainAppMenu Activity", "RED", 150, 200, 400);

        //Graphic Settings
    }
}
