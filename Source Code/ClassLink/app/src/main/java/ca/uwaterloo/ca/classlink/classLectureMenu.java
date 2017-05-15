package ca.uwaterloo.ca.classlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;
import ca.uwaterloo.ca.classlink.MiscHelperClasses.viewHelperClass;

public class classLectureMenu extends AppCompatActivity implements activityParameters {

    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lecture_menu);
    }

    @Override
    public void layoutSetup() {

    }
}
