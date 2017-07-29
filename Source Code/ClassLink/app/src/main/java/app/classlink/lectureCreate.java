package app.classlink;

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

import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.teacher.teacher;
import app.classlink.backend.users.teacher.teacherDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class lectureCreate extends baseActivity implements activityParameters {

    protected ImageView line, submitForm;
    protected EditText lectureName, lectureDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_create);

        layoutSetup();
        createForm();
        submitFormListener();
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
        this.viewHelperClass.addGraphicInputBox(lectureName, "e.g: ECE 155, Calculus 1", R.drawable.inputbox, InputType.TYPE_CLASS_TEXT, 50, 25, 0.8f, 0.8f);

        this.viewHelperClass.addText("Lecture Group description:", "OpenSans-Semibold", "BLACK", 2, 15, 50, 33);
        this.lectureDescription = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox(lectureDescription, "e.g: Taught by Akosh Nagy" , R.drawable.inputbox, InputType.TYPE_CLASS_TEXT, 50, 39, 0.8f, 0.8f);

        this.viewHelperClass.addText("Choose your institution", "OpenSans-Semibold", "BLACK", 2, 15, 50, 47);

        this.submitForm = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(submitForm, "Create Group!", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 50, 65, 0.5f, 0.5f);
    }

    /**
     * @Method submitFormListener : submits the inputted information and creates the new group
     */
    private void submitFormListener() {
        this.submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (viewHelperClass.isEditTextEmpty(new ArrayList<>(Arrays.asList(lectureName,lectureDescription)))){
                   //temp new teacher
                   teacher testTeacher = new teacher("Rahul", "Chakravarthy", "SaltyDoge", "How old am I?", "19", School.UNIVERSITY_OF_WATERLOO);
                   teacherDAO teacherDAO = new teacherDAO();
                   teacherDAO.createTeacher(testTeacher);

                   //create the group and append it to the database
                   LectureGroupDAO lectureGroupDAO = new LectureGroupDAO();
                   if (!lectureGroupDAO.createLectureGroup(lectureName.getText().toString().trim(), lectureDescription.getText().toString().trim(), testTeacher, School.UNIVERSITY_OF_WATERLOO)){
                       Toast.makeText(viewHelperClass.getActivityContext(), "Error! Group Name already exists", Toast.LENGTH_LONG).show();
                       return;
                   }
                   Toast.makeText(viewHelperClass.getActivityContext(), "Lecture Group Created to view/change settings visit the settings menu", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(lectureCreate.this, lectureRoom.class));

               } else {
                   Toast.makeText(viewHelperClass.getActivityContext(), "Error, all fields must be filled", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}
