package app.classlink.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.teacher.teacher;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class lectureCreate extends baseActivity implements activityParameters {

    protected ImageView line, submitForm;
    protected EditText lectureName, lectureDescription;

    protected LectureGroupDAO lectureGroupDAO; //for lectureGroup queries
    protected userDAO userDAO; //for user queries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_create);

        layoutSetup();
        createForm();
        submitFormListener();
        setActivityDAOListeners();
    }

    /**
     * @Method onStart : verify user authentication
     */
    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(lectureCreate.this, login.class));
        }
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();

        this.lectureGroupDAO = new LectureGroupDAO();
        this.lectureGroupDAO.setCacheListener(School.UNIVERSITY_OF_WATERLOO.toString());
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_lecture_create);
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        this.viewHelperClass.addText("Create a Lecture\n         Group", "OpenSans-Bold", "BLACK", 2, 30, 50, 8);

        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 15, 0.75f, 1f, false);
    }

    /**
     * @Method createForm : Display all the front end to create a lectureGroup
     */
    private void createForm() {
        this.viewHelperClass.addText("Lecture Group Name:", "OpenSans-Semibold", "BLACK", 2, 15, 50, 19);
        this.lectureName = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("e.g: ECE 155, Calculus 1", R.drawable.inputbox, lectureName, InputType.TYPE_CLASS_TEXT, 15, 50, 25, 0.8f, 0.8f);

        this.viewHelperClass.addText("Lecture Group description:", "OpenSans-Semibold", "BLACK", 2, 15, 50, 33);
        this.lectureDescription = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("e.g: Taught by Akosh Nagy", R.drawable.inputbox, lectureDescription, InputType.TYPE_CLASS_TEXT, 15, 50, 39, 0.8f, 0.8f);

        this.submitForm = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submitForm, "Create Group!", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 50, 55, 0.5f, 0.5f);
    }

    /**
     * @Method submitFormListener : submits the inputted information and creates the new group
     */
    private void submitFormListener() {
        this.submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (viewHelperClass.isEditTextEmpty(new ArrayList<>(Arrays.asList(lectureName,lectureDescription)))){
                   if (lectureGroupDAO.createLectureGroup(lectureName.getText().toString().trim(), lectureDescription.getText().toString().trim(),
                           (teacher) userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()), userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getSchool())){
                       Toast.makeText(viewHelperClass.getActivityContext(), "Lecture Group Created to view/change settings visit the settings menu", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(viewHelperClass.getActivityContext(), "Error! Group Name already exists", Toast.LENGTH_LONG).show();
                   }
               } else {
                   Toast.makeText(viewHelperClass.getActivityContext(), "Error, all fields must be filled", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}
