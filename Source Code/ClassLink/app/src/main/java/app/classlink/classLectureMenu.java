package app.classlink;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class classLectureMenu extends AppCompatActivity implements activityParameters {

    protected RelativeLayout classLectureMenuLayout;
    protected app.classlink.helperClasses.viewHelperClass viewHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lecture_menu);

        classLectureMenuLayout = (RelativeLayout) findViewById(R.id.activity_class_lecture_menu);
        viewHelperClass = new viewHelperClass(classLectureMenuLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.classLectureMenuLayout.setBackgroundResource(R.drawable.bg);
    }
}
