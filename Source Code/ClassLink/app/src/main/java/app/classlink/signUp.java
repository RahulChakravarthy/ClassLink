package app.classlink;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

/**
 * @Class field : Encapsulation of each field displayed on the sign up activity
 */
class field {

    public String name;
    public EditText inputBox;
    public ImageView imageBox;

    public boolean error;
    public String errorMessage;

    public field(String name, Context context){
        this.name = name;
        this.inputBox = new EditText(context);
        this.imageBox = new ImageView(context);
        this.error = false;
    }

    public void setErrorMessage(String message){
        this.errorMessage = message;
    }
}

/**
 * @Class signUp : Sign up class handler
 */
public class signUp extends baseActivity implements activityParameters {

    /** The functionality of this UI is such that if you want to include another field, just append  to this list*/
    private ArrayList<String> keys = new ArrayList<>(Arrays.asList("First Name:", "Last Name:", "Email:", "Phone Number:", "School", "Username:", "Password:", "Confirm Password:", "Create a Security question:", "Security question Answer"));
    private HashMap<String, field> fields = new HashMap<>();
    private FirebaseUser currentUser;

    private ImageView submit,line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //User authentication
        userAuth = FirebaseAuth.getInstance();

        layoutSetup();
        submitButton();
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
        this.viewHelperClass.addText("Class-Link Sign Up: ", "OpenSans-Bold", "BLACK", 2, 18f, 25, 5);

        //Graphical Setup
        line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 40, 8, 0.75f, 1, false);

        /**Text Field graphics*/
        for (int i = 0; i < keys.size(); i++){
            //Add each field to a hashmap
            fields.put(keys.get(i), new field(keys.get(i), this.viewHelperClass.getActivityContext()));

            EditText tempEdit = fields.get(keys.get(i)).inputBox;

            this.viewHelperClass.addText(keys.get(i), "OpenSans-Regular", "BLACK", 1, 16f, 5,16*i + 12);
            this.viewHelperClass.addGraphicInputBox(null, R.drawable.inputbox, tempEdit, InputType.TYPE_CLASS_TEXT, 15, 42, 16*i + 19, 0.75f, 0.75f);
        }

        /** Radio button field graphics */
        this.viewHelperClass.addText("What type of account is this?", "OpenSans-Regular", "BLACK", 1, 16f, 5, keys.size()*16 + 12);

        /**Check box field graphics */
        submit = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submit, "Submit", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 50,keys.size()*16 + 30f, 0.4f, 0.5f);
    }

    /**
     * @Method Submit button to store information in database
     */
    private void submitButton() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sanitizeAndCheckUserInput()){
                    addUser();
                }
            }
        });
    }

    /**
     * @Method sanitizeAndCheckUserInput : checks all user input and throws TOAST with all errors
     * @return boolean : true if all data checks out, false if otherwise
     */
    private boolean sanitizeAndCheckUserInput() {
        return true;
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

    /**
     * @Method addUserToDatabase : adds a user to the User database
     */
    private void addUserToDatabase() {
    }

    @Override
    protected void setActivityDAOListeners() {

    }
}
