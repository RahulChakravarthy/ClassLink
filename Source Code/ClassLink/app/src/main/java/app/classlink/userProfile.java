package app.classlink;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class userProfile extends baseActivity implements activityParameters {

    private ImageView profilePhotoField;
    private ImageView scoreField;
    private ImageView userDetails;
    private ImageView bestQuestions;
    private ImageView bestAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_user_profile);
        viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundColor(Color.parseColor("#3d80b0"));

        profilePhotoField = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(profilePhotoField, R.drawable.button_profile, -145, -150, 0.55f, 0.55f, false);

        scoreField = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(scoreField, R.drawable.button_wide, -258, 420, 0.4f, 0.6f, false);

        userDetails = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(userDetails, R.drawable.field_large, 250, -220, 0.5f, 1.4f, false);

        bestQuestions = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(bestQuestions, R.drawable.field_large, 0, 790, 1.0f, 1.0f, false);

        bestAnswers = new ImageView(getApplicationContext());
        viewHelperClass.addGraphics(bestAnswers, R.drawable.field_large, 0, 350, 1.0f, 1.0f, false);
    }
}
