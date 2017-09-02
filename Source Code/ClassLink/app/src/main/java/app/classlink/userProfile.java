package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.backend.core.baseActivity;
import app.classlink.backend.users.user.user;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class userProfile extends baseActivity implements activityParameters {

    private user currentUser;

    private ImageView blockBackground;
    private ImageView profilePhotoField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        coreSetup();
        layoutSetup();

    }

    @Override
    protected void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(userProfile.this, login.class));
        }
    }

    /**
     * @Method coreSetup : setup core app data used throughout this activity
     */
    private void coreSetup() {
        this.currentUser = (user) getIntent().getExtras().get("user");
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_user_profile);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.bg);
        this.profilePhotoField = (ImageView) findViewById(R.id.imgProfile);
        this.blockBackground = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(blockBackground, R.drawable.button_wide_grey, 0, 700, 0.975f, 8.0f, false);

        //Add profile info
        //First Name
        this.viewHelperClass.addText("First Name:", "OpenSans-Semibold", "BLACK", 1, 20, 5, 33);
        this.viewHelperClass.addText(this.currentUser.getFirstName(), "OpenSans-ExtraBold", "BLACK", 1, 20, 55, 33);
        //Last Name
        this.viewHelperClass.addText("Last Name:", "OpenSans-Semibold", "BLACK", 1, 20, 5, 40);
        this.viewHelperClass.addText(this.currentUser.getLastName(), "OpenSans-ExtraBold", "BLACK", 1, 20, 55, 40);
        //Registered Email
        this.viewHelperClass.addText("User Email:", "OpenSans-Semibold", "BLACK", 1, 20, 5, 47);
        this.viewHelperClass.addText(this.currentUser.getEmail(), "OpenSans-ExtraBold", "BLACK", 1, 15, 40, 47.5f);
        //Profile Account Type
        this.viewHelperClass.addText("Account Type:", "OpenSans-Semibold", "BLACK", 1, 20, 5, 55);
        this.viewHelperClass.addText(this.currentUser.getClass().getSimpleName(), "OpenSans-ExtraBold", "BLACK", 1, 20, 55, 54.5f);
        //Profile Score
        this.viewHelperClass.addText("Total User Score:", "OpenSans-Semibold", "BLACK", 1, 20, 5, 63);
        this.viewHelperClass.addText(String.valueOf(this.currentUser.getUserScore()), "OpenSans-ExtraBold", "BLACK", 1, 20, 60, 62.5f);
        //More Details soon
        this.viewHelperClass.addText("  Report and User Feedback\n  functionality coming soon!", "OpenSans-Semibold", "BLACK", 2, 20, 50, 80);
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
    }
}
