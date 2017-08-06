package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Arrays;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.administrator.administrator;
import app.classlink.backend.users.student.student;
import app.classlink.backend.users.teacher.teacher;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

/**
 * @Class signUp : Sign up class handler
 */
public class signUp extends baseActivity implements activityParameters {

    //Graphics and back end settings
    private ImageView submit,line;

    //User form information
    private EditText firstName, lastName, password, confirmPassword, email;
    private Spinner schoolMenu, userType;

    //DAOS
    private userDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userAuth = FirebaseAuth.getInstance();

        layoutSetup();
        setActivityDAOListeners();
        submitButton();
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
        this.viewHelperClass.addText("Sign Up:", "OpenSans-Bold", "BLACK", 2, 23, 50, 5);

        //Form set up
        this.viewHelperClass.addText("First Name:", "OpenSans-Regular", "BLACK", 2, 17, 25, 12);
        this.firstName = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, firstName, InputType.TYPE_CLASS_TEXT, 15, 25, 18, 0.4f, 0.85f);

        this.viewHelperClass.addText("Last Name:", "OpenSans-Regular", "BLACK", 2, 17, 75, 12);
        this.lastName = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, lastName, InputType.TYPE_CLASS_TEXT, 15, 75, 18, 0.4f, 0.85f);

        this.viewHelperClass.addText("Password:", "OpenSans-Regular", "BLACK", 2, 17, 25, 27);
        this.password = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, password, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 25, 33, 0.4f, 0.85f);

        this.viewHelperClass.addText("Confirm Password:", "OpenSans-Regular", "BLACK", 2, 17, 75, 27);
        this.confirmPassword = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, confirmPassword, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 75, 33, 0.4f, 0.85f);

        this.viewHelperClass.addText("Email Address:", "OpenSans-Regular", "BLACK", 2, 17, 50, 41);
        this.email = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, email, InputType.TYPE_CLASS_TEXT, 15, 50, 47, 0.7f, 0.85f);

        this.userType = new Spinner(getApplicationContext());
        this.viewHelperClass.addSpinner(userType, new ArrayList<>(Arrays.asList("STUDENT", "TEACHER", "ADMINISTRATOR")), schoolMenuListener(), android.R.layout.simple_spinner_dropdown_item, 50, 60, 1, 1);

        this.schoolMenu = new Spinner(getApplicationContext());
        ArrayList<String> schools = new ArrayList<>();
        for (School school : School.values()) {
            schools.add(school.name().replaceAll("[_]", " "));
        }
        this.viewHelperClass.addSpinner(schoolMenu, schools, schoolMenuListener(), android.R.layout.simple_spinner_dropdown_item, 50, 70, 1, 1);

        //Graphical Setup
        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 8, 0.75f, 1, false);

        this.submit = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submit, "Create an account!", 15, "OpenSans-Regular", "BLACK" ,R.drawable.curvedbutton, 50, 85, 0.5f, 0.5f);

    }

    /**
     * @Method schoolMenuListener : creates a down menu to choose between schools
     */
    private AdapterView.OnItemSelectedListener schoolMenuListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            }
        };
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
        } else if (userDAO.getUserByEmail(email.getText().toString()) != null){
            return 2; //user email already exists
        }
        return 69;
    }

    /**
     * @Method addUser : Signs user up to user list and adds them to the user database
     */
    private void addUser() {
        userAuth.createUserWithEmailAndPassword(this.email.getText().toString().trim(), this.password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    addUserToDatabase();
                    emailVerifyUser();
                    Toast.makeText(viewHelperClass.getActivityContext(), "User added!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(signUp.this, login.class));
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
        if (userType.getSelectedItem().toString().equals("STUDENT")){
            student newStudent = new student(firstName.getText().toString().trim(), lastName.getText().toString().trim(),
                    email.getText().toString().trim(), School.valueOf(schoolMenu.getSelectedItem().toString().replaceAll("\\s", "_")));
            userDAO.createUser(newStudent);
        } else if (userType.getSelectedItem().toString().equals("TEACHER")) {
            teacher newTeacher = new teacher(firstName.getText().toString().trim(), lastName.getText().toString().trim(),
                    email.getText().toString().trim(), School.valueOf(schoolMenu.getSelectedItem().toString().replaceAll("\\s", "_")));
            userDAO.createUser(newTeacher);
        } else {
            administrator newAdmin = new administrator(firstName.getText().toString().trim(), lastName.getText().toString().trim(),
                    email.getText().toString().trim(), School.valueOf(schoolMenu.getSelectedItem().toString().replaceAll("\\s", "_")));
            userDAO.createUser((newAdmin));
        }
    }
}
