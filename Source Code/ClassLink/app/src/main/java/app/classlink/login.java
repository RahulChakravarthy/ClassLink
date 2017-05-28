package app.classlink;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

/**
 * @Class login: This is the login activity for Class Link
 */
public class login extends AppCompatActivity implements activityParameters {

    protected RelativeLayout loginLayout; //Activity Layout
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
        this.loginLayout = (RelativeLayout) findViewById(R.id.activity_login);
        this.loginLayout.setBackgroundColor(Color.WHITE);

        //Text Settings
        this.viewHelperClass = new viewHelperClass(loginLayout, getApplicationContext());
        this.viewHelperClass.addText("UserName: ", "BLACK", 12, 400, 420);
        this.viewHelperClass.addText("Password", "BLACK", 12, 100,100);

        //Graphical Settings (only static images and logos)


    }

    public void usernameInput(){

    }

    public void passwordInput(){

    }

    public void loginListener(){
        //Create login button
        Button login = new Button(getApplicationContext());
        String buttonText = "To the main app menu";
        login.setText(buttonText);
        this.viewHelperClass.addGraphics(login, 300, 1000);

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
        Button signUp = new Button(getApplicationContext());
        String buttonText = "To the sign up page";
        signUp.setText(buttonText);
        this.viewHelperClass.addGraphics(signUp, 300, 1200);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(login.this, app.classlink.mainAppMenu.class));}
        });
    }


}

