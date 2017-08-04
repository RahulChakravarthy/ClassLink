package app.classlink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

/**
 * @Class signUp : Sign up class handler
 */
public class signUp extends baseActivity implements activityParameters {

    //Graphics and back end settings
    private FirebaseUser currentUser;
    private ImageView submit,line;

    //User form information
    private EditText firstName, lastName, password, confirmPassword, email;
    private RadioGroup userType;

    //DAOS
    private userDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //User authentication
        userAuth = FirebaseAuth.getInstance();

        layoutSetup();
        submitButton();
        setActivityDAOListeners();
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Setup
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_sign_up);
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        //Text Setup
        this.viewHelperClass.addText("Sign Up: ", "OpenSans-Bold", "BLACK", 2, 23, 50, 5);

        //Form set up
        this.firstName = new EditText(getApplicationContext());

        this.lastName = new EditText(getApplicationContext());

        this.password = new EditText(getApplicationContext());

        this.confirmPassword = new EditText(getApplicationContext());

        this.email = new EditText(getApplicationContext());

        this.userType = new RadioGroup(getApplicationContext());


        //Graphical Setup
        line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 40, 8, 0.75f, 1, false);

        submit = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submit, "Create an account", 18, "OpenSans-Regular", "BLACK" ,R.drawable.curvedbutton, 50, 90, 0.5f, 0.5f);

    }

    /**
     * @Method Submit button to store information in database
     */
    private void submitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (sanitizeAndCheckUserInput()){
                    case 0: //missing input information
                        Toast.makeText(viewHelperClass.getActivityContext(), "Error: all fields must be filled", Toast.LENGTH_SHORT).show();
                        break;
                    case 1: //passwords do not match
                        Toast.makeText(viewHelperClass.getActivityContext(), "Error: passwords do not match", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: //email already exists
                        Toast.makeText(viewHelperClass.getActivityContext(), "Error: email already exists in our databases", Toast.LENGTH_SHORT).show();
                        break;
                    default : //all input info is correct
                        addUser();
                        startActivity(new Intent(signUp.this, login.class));
                        break;
                }
            }
        });
    }

    /**
     * @Method sanitizeAndCheckUserInput : checks all user input and throws TOAST with all errors
     * @return boolean : true if all data checks out, false if otherwise
     */
    private int sanitizeAndCheckUserInput() {
        if (!this.viewHelperClass.isEditTextEmpty(new ArrayList<>(Arrays.asList(firstName, lastName, email, password, confirmPassword)))){
            return 0; //missing input information
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            return 1; //passwords do not match
        } else if (userDAO.getUserByEmail(email.getText().toString()) == null){
            return 2; //user email already exists
        }
        return 69;
    }

    /**
     * @Method addUser : Signs user up to user list and adds them to the user database
     */
    private void addUser() {
        userAuth.createUserWithEmailAndPassword("rahul.chakravarthy101@gmail.com", "TESTING").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    addUserToDatabase();
                    emailVerifyUser();
                    currentUser = userAuth.getCurrentUser();
                } else {
                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Toast.makeText(viewHelperClass.getActivityContext(), "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * @Method emailVerifyUser : sends and email to the user to verify their account
     */
    private void emailVerifyUser(){

    }

    /**
     * @Method addUserToDatabase : adds a user to the User database
     */
    private void addUserToDatabase() {
        //add user based on what type they are (teacher or student)
    }
}
