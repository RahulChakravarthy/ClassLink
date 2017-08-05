package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

public class settings extends baseActivity implements activityParameters {

    EditText changeUsername, changePassword, confirmPassword, changeEmail;
    ImageView submitForm, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //User authentication
        userAuth = FirebaseAuth.getInstance();

        layoutSetup();
        logUserOut();
        updateUserDetails();
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
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_settings);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        // Text based graphics
        this.viewHelperClass.addText("Settings", "OpenSans-ExtraBold", "BLACK", 2, 30, 50, 5);
        this.viewHelperClass.addText("Change User Details:", "OpenSans-Semibold", "BLACK", 2, 21, 50, 13);
        this.viewHelperClass.addText("(only filled boxes will modify profile data)", "OpenSans-Semibold", "BLACK", 2, 17, 50, 18);

        this.viewHelperClass.addText("New Username:", "OpenSans-Regular", "BLACK", 1, 15, 2, 25);
        this.changeUsername = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changeUsername, InputType.TYPE_CLASS_TEXT, 15, 66.7f, 26.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("New Password:", "OpenSans-Regular", "BLACK", 1, 15, 2, 33);
        this.changePassword = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changePassword, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 66.7f, 34.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("Confirm\nPassword:", "OpenSans-Regular", "BLACK", 1, 15, 2, 39);
        this.changePassword = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changePassword, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 66.7f, 42.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("New Email:", "OpenSans-Regular", "BLACK", 1, 15, 2, 49);
        this.changeEmail = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changeEmail, InputType.TYPE_CLASS_TEXT, 15, 66.7f, 50.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("New Institution:", "OpenSans-Regular", "BLACK", 1, 15, 2, 57);

        // Graphical Graphics
        this.viewHelperClass.addGraphics(new ImageView(getApplicationContext()), R.drawable.line, 50, 9, 0.5f, 1, false);

        this.submitForm = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submitForm, "Update Profile!", 15, "OpenSans-Semibold", "BLACK", R.drawable.curvedbutton, 50, 70, 0.5f, 0.5f);

        this.logout = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(logout, "Logout", 15, "OpenSans-Semibold", "BLACK", R.drawable.curvedbutton, 25, 90, 0.4f, 0.5f);
    }

    /**
     * @Method updateUserDetails : gets new user details and updates them both in the database and user authentication table
     */
    private void updateUserDetails() {
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(viewHelperClass.getActivityContext(), "Profile Details Updated", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * @Method logUserOut : logs current user out of the app
     */
    private void logUserOut() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAuth.signOut(); //call this when you get user Auth working
                Toast.makeText(viewHelperClass.getActivityContext(), "Logging Out...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(settings.this, login.class));
            }
        });
    }
}
