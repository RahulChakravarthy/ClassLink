package app.classlink;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class joinLectureGroupMenu extends baseActivity implements activityParameters {

    ImageView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_lecture_group_menu);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_join_lecture_group_menu);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        /** Text based graphics */
        this.viewHelperClass.addText("Join a Group", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        this.viewHelperClass.addText("Search for a group", "OpenSans-Regular", "BLACK", 2, 15, 50, 15);

        /** Graphic based Graphics*/
        line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 7,0.5f, 1, false);

        //RecycleViewer

    }
}
