package app.classlink;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

/**
 * @Class login: This is the login activity for Class Link
 */
public class login extends baseActivity implements activityParameters {

    ImageView logo, login, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Set up all activity listeners and textboxes
         */
        layoutSetup();
        usernameInput();
        passwordInput();
        loginListener();
        signUpListener();
    }

    /**
     * @Method  layoutSetup : Sets up all UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Settings
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_login);
        this.activityLayout.setBackgroundResource(R.drawable.bg);

        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext());

        //Graphical Settings (only static images and logos)
        logo = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(logo, R.drawable.logo, 0, 0, 1,1, false);
        logo.setAlpha(0.75f);
        login = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(login, R.drawable.button_wide_grey, -150, 1325, 0.25f, 0.5f, true);
        signUp = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(signUp, R.drawable.button_wide_grey, 200, 1325, 0.25f, 0.5f, true);

        //Text Settings
        this.viewHelperClass.addText("Login", "BLACK", 15, 330, 1450);
        this.viewHelperClass.addText("Sign Up", "BLACK", 15, 660, 1450);
        this.viewHelperClass.addText("UserName:", "BLACK", 15, 450, 1000);
        this.viewHelperClass.addText("Password:", "BLACK", 15, 450,1200);

    }

    public void usernameInput(){

    }

    public void passwordInput(){

    }

    /**
     * @Method loginListener : Take user to the main app menu
     */
    public void loginListener(){
        //Trigger firebase query to validate user credentials when button is clicked
        login.setOnClickListener(new View.OnClickListener(){
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
}

