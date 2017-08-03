package app.classlink;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

/**
 * @Class login: This is the login activity for Class Link
 */
public class login extends baseActivity implements activityParameters  {

    private TextView forgotPassword; //forgot password button
    private ImageView login, signUp, line; //front end graphic buttons/models
    private EditText emailInput, passwordInput; //text input boxes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //User authentication
        userAuth = FirebaseAuth.getInstance();

         // Set up all activity listeners
        layoutSetup();
        loginListener();
        signUpListener();
        forgotPasswordListener();
    }

    /**
     * @Method onStart : verify if the user is already logged in
     */
    @Override
    protected void onStart(){
        super.onStart();
        if (this.userAuth.getCurrentUser() != null){
            startActivity(new Intent(login.this, mainMenu.class));
        }
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
        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 18, 0.75f, 1, false);

        this.emailInput = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, emailInput, InputType.TYPE_CLASS_TEXT, 17, 50, 30, 0.75f, 0.8f);

        this.passwordInput = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, passwordInput, InputType.TYPE_TEXT_VARIATION_PASSWORD, 17, 50, 44, 0.75f, 0.8f);

        this.login = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(login, "Login", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 34, 55, 0.3f, 0.4f);

        this.signUp = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(signUp, "Sign Up", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 66, 55, 0.3f, 0.4f);

        //Text Settings
        this.viewHelperClass.addText("CLASS-LINK", "OpenSans-ExtraBoldItalic", "BLACK", 2, 50, 50, 13);//title
        this.viewHelperClass.addText("Email Address:", "OpenSans-Semibold", "BLACK", 2, 17, 50, 23); //user Name
        this.viewHelperClass.addText("Password:", "OpenSans-Semibold", "BLACK", 2, 17, 50, 37);// Password

        this.forgotPassword = new TextView(getApplicationContext());
        this.viewHelperClass.addText(forgotPassword, "Forgot Password?",  "OpenSans-Bold", "BLACK", 15, 50, 65, true);
    }

    /**
     *@Method loginListener : Processes login and verifies user information
     */
    private void loginListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHelperClass.isEditTextEmpty(new ArrayList<>(Arrays.asList(emailInput,passwordInput)))){
                    authenticateUser();
                } else {
                    Toast.makeText(viewHelperClass.getActivityContext(), "Please enter email and password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     * @Method authenticateUser : Authenticate the user
     */
    @SuppressWarnings("ALL")
    private void authenticateUser() {
        if (internetConnection()) {
            userAuth.signInWithEmailAndPassword(emailInput.getText().toString().trim(), passwordInput.getText().toString().trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(login.this, mainMenu.class));
                    } else {
                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                        Toast.makeText(viewHelperClass.getActivityContext(), "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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
    public void forgotPasswordListener() {
        if (internetConnection()) {
            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(viewHelperClass.getActivityContext(), "Feature not implemented yet", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(login.this, login.class));
                }
            });
        }
    }

    @Override
    protected void setActivityDAOListeners() {
        //No DAOs being accessed
    }
}

