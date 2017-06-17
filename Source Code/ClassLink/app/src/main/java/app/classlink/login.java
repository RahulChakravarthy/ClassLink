package app.classlink;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

/**
 * @Class login: This is the login activity for Class Link
 */
public class login extends baseActivity implements activityParameters {

    TextView forgotPassword; //forgot password button
    ImageView login, signUp, line; //front end graphic buttons/models
    EditText usernameInput, passwordInput; //textinput boxes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Set up all activity listeners and textboxes
         */
        layoutSetup();
        loginListener();
        signUpListener();
        forgotPasswordListener();

    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Settings
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_login);

        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        //Graphical Settings (only static images and logos)
        line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 18, 0.75f, 1, false);

        usernameInput = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox(usernameInput,R.drawable.inputbox, InputType.TYPE_CLASS_TEXT, 50, 30, 0.75f, 0.8f);

        passwordInput = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox(passwordInput, R.drawable.inputbox, InputType.TYPE_TEXT_VARIATION_PASSWORD, 50, 44, 0.75f, 0.8f);

        login = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(login, "Login", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 34, 55, 0.3f, 0.4f);

        signUp = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(signUp, "Sign Up", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 66, 55, 0.3f, 0.4f);

        //Text Settings
        this.viewHelperClass.addText("CLASS-LINK", "OpenSans-ExtraBoldItalic", "BLACK", 50, 50, 13);//title
        this.viewHelperClass.addText("User Name:", "OpenSans-Regular", "BLACK", 15, 50, 23); //User Name
        this.viewHelperClass.addText("Password:", "OpenSans-Regular", "BLACK", 15, 50, 37);// Password

        forgotPassword = new TextView(getApplicationContext());
        this.viewHelperClass.addText(forgotPassword, "Forgot Password?",  "OpenSans-Bold", "BLACK", 15, 50, 65, true);

    }

    /**
     *@Method loginListener : Processes login and verifies user information
     */
    private void loginListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(login.this, app.classlink.mainAppMenu.class));}
        });
    }

    /**
     * @Method signUpListener : Take user to the sign up activity where they can make a new account
     */
    public void signUpListener(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(login.this, app.classlink.signUp.class));}
        });
    }

    /**
     * @Method forgotPasswordHandler : handles case where user has forgotten password
     */
    public void forgotPasswordListener(){
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(login.this, app.classlink.signUp.class));}
        });
    }
}

