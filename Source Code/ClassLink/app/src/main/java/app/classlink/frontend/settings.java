package app.classlink.frontend;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class settings extends baseActivity implements activityParameters {

    EditText changePassword, confirmPassword, changeEmail;
    ImageView submitForm, logout, deleteAccount;
    Spinner schoolMenu, changePermissions;

    //DAOs
    userDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        layoutSetup();
        setActivityDAOListeners();
        logUserOut();
        updateUserDetails();
        deleteUser();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(settings.this, login.class));
        }
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDao = new userDAO();
        this.userDao.setCacheListener();
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

        this.viewHelperClass.addText("New Permission:", "OpenSans-Regular", "BLACK", 1, 15, 2, 25);
        this.changePermissions = new Spinner(getApplicationContext());
        ArrayList<String> permissions = new ArrayList<>(Arrays.asList("STUDENT", "TEACHER", "ADMINISTRATOR"));
        this.viewHelperClass.addSpinner(this.changePermissions, permissions, permissionsListener(), R.drawable.custom_spinner, android.R.layout.simple_spinner_dropdown_item, 73, 26, 1,1);

        this.viewHelperClass.addText("New Password:", "OpenSans-Regular", "BLACK", 1, 15, 2, 33);
        this.changePassword = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changePassword, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 66.7f, 34.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("Confirm\nPassword:", "OpenSans-Regular", "BLACK", 1, 15, 2, 39);
        this.confirmPassword = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, confirmPassword, InputType.TYPE_TEXT_VARIATION_PASSWORD, 15, 66.7f, 42.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("New Email:", "OpenSans-Regular", "BLACK", 1, 15, 2, 49);
        this.changeEmail = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("", R.drawable.inputbox, changeEmail, InputType.TYPE_CLASS_TEXT, 15, 66.7f, 50.5f, 0.64f, 0.75f);

        this.viewHelperClass.addText("New Institution:", "OpenSans-Regular", "BLACK", 1, 15, 2, 57);
        this.schoolMenu = new Spinner(getApplicationContext());
        ArrayList<String> schools = new ArrayList<>();
        for (School school : School.values()) {
            schools.add(school.name().replaceAll("[_]", " "));
        }
        this.viewHelperClass.addSpinner(schoolMenu, schools, schoolMenuListener(), R.drawable.custom_spinner, android.R.layout.simple_spinner_dropdown_item, 73, 58.5f, 1, 1);


        // Graphical Graphics
        this.viewHelperClass.addGraphics(new ImageView(getApplicationContext()), R.drawable.line, 50, 9, 0.5f, 1, false);

        this.submitForm = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submitForm, "Update Profile!", 15, "OpenSans-Semibold", "BLACK", R.drawable.curvedbutton, 50, 70, 0.5f, 0.5f);

        this.logout = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(logout, "Logout", 15, "OpenSans-Semibold", "BLACK", R.drawable.curvedbutton, 25, 90, 0.4f, 0.5f);

        this.deleteAccount = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(deleteAccount, "Delete Account", 15, "OpenSans-Semibold", "BLACK", R.drawable.curvedbutton, 75, 90, 0.4f, 0.5f);
    }

    /**
     * @Method schoolMenuListener : creates a down menu to choose between user permissions
     */
    private AdapterView.OnItemSelectedListener permissionsListener(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
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
            }
        };
    }

    /**
     * @Method updateUserDetails : gets new user details and updates them both in the database and user authentication table
     */
    private void updateUserDetails() {
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHelperClass.clearAllEditTexts(new ArrayList<>(Arrays.asList(changePassword, confirmPassword, changeEmail)));
                Toast.makeText(viewHelperClass.getActivityContext(), "Profile details will be updated shortly", Toast.LENGTH_LONG).show();
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

    /**
     * @Method deleteUser : deletes the current user from the system
     */
    private void deleteUser() {
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                userDao.deleteUserByEmail(userDao.getUserByEmail(userAuth.getCurrentUser().getEmail()), userAuth);
//                startActivity(new Intent(settings.this, login.class));
                Toast.makeText(viewHelperClass.getActivityContext(), "Notification sent to administrator, your account will be deleted shortly...", Toast.LENGTH_LONG).show();
            }
        });
    }
}
