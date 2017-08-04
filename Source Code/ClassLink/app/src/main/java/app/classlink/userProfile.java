package app.classlink;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

public class userProfile extends baseActivity implements activityParameters {

    private ImageView blockBackground;
    private ImageView profilePhotoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_user_profile);
        viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
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
        //this.activityLayout.setBackgroundColor(Color.parseColor("#3d80b0"));
        this.activityLayout.setBackgroundResource(R.drawable.bg);

        profilePhotoField = (ImageView) findViewById(R.id.imgProfile);

        blockBackground = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(blockBackground, R.drawable.button_wide_grey, 0, 700, 0.975f, 8.0f, false);
    }
}
