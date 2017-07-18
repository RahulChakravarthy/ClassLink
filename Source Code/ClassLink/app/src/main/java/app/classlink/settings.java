package app.classlink;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class settings extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_settings);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        // Text based graphics
        this.viewHelperClass.addText("Settings", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        this.viewHelperClass.addText("First Name:", "OpenSans-Regular", "BLACK", 1, 15, 10, 13);
        this.viewHelperClass.addText("Last Name:", "OpenSans-Regular", "BLACK", 1, 15, 10, 15);
        this.viewHelperClass.addText("Username:", "OpenSans-Regular", "BLACK", 1, 15, 10, 20);
        this.viewHelperClass.addText("Email:", "OpenSans-Regular", "BLACK", 1, 15, 10, 25);
        this.viewHelperClass.addText("Enable Notifications:", "OpenSans-Regular", "BLACK", 1, 15, 10, 30);
        //Get user information


        // Graphical Graphics
        this.viewHelperClass.addGraphics(new ImageView(getApplicationContext()), R.drawable.line, 50, 8, 0.5f, 1, false);
    }
}
