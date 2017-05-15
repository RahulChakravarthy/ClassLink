package ca.uwaterloo.ca.classlink;


import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import ca.uwaterloo.ca.classlink.MiscHelperClasses.activityParameters;
import ca.uwaterloo.ca.classlink.MiscHelperClasses.viewHelperClass;

/**
 * @Class login: This is the login activity for Class Link
 */
public class login extends AppCompatActivity implements activityParameters {

    protected RelativeLayout login; //Activity Layout
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity

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
        login = (RelativeLayout) findViewById(R.id.activity_login);
        login.setBackgroundColor(Color.WHITE);

        //Text Settings
        viewHelperClass = new viewHelperClass(login, getApplicationContext());
        viewHelperClass.addText("Hello World", "BLUE", 20, 69, 420);

        //Graphical Settings


    }

    public void usernameInput(){

    }

    public void passwordInput(){

    }

    public void loginListener(){

    }

    public void signUpListener(){

    }


}
