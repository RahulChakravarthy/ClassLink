package ca.uwaterloo.ca.classlink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;
import ca.uwaterloo.ca.classlink.MiscHelperClasses.viewHelperClass;

public class classLectureMenu extends AppCompatActivity implements activityParameters {

    protected RelativeLayout classLectureMenuLayout;
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lecture_menu);

        /**
         * Set up all activity listeners and textboxes
         */
        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Settings
        this.classLectureMenuLayout = (RelativeLayout) findViewById(R.id.activity_class_lecture_menu);
        this.classLectureMenuLayout.setBackgroundColor(Color.WHITE);

        //Text Settings
        this.viewHelperClass.addText("KILL EVERYONE", "RED", 100, 100, 100);

        //Graphic Settings
    }

}
