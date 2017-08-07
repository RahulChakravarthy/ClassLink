package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.administrator.administrator;
import app.classlink.backend.users.user.user;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.recyclerAdapters.displayLectureGroupsAdapter;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

public class lectureJoin extends baseActivity implements activityParameters {

    private ImageView createLectureGroup, genericLectureRoom, line;
    private EditText searchBox;
    private RecyclerView groupList;
    private displayLectureGroupsAdapter groupListAdapter;
    private LinearLayoutManager groupLayout;

    //DAOs
    private userDAO userDAO;
    private LectureGroupDAO lectureGroupDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_lecture_join);

        layoutSetup();
        setActivityDAOListeners();
        groupListSetup();
        createLectureGroupListener();
        genericLectureRoomListener();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(lectureJoin.this, login.class));
        }
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();

        this.lectureGroupDAO = new LectureGroupDAO();
        user currentUser = ((user) getIntent().getSerializableExtra("user")); //get the user passed in by the previous activity quickly
        this.lectureGroupDAO.setCacheListener(currentUser.getSchool().toString());
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_lecture_join);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        // Text based graphics
        this.viewHelperClass.addText("Join a Group", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        this.viewHelperClass.addText("Search for a group", "OpenSans-Regular", "BLACK", 2, 15, 50, 15);

        // Graphic based Graphics
        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(this.line, R.drawable.line, 50, 8,0.5f, 1, false);

        this.createLectureGroup = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(this.createLectureGroup, "Create Lecture Group", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 30f, 90f, 0.5f,0.5f);

        this.searchBox = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox(null, R.drawable.inputbox, this.searchBox, InputType.TYPE_CLASS_TEXT, 15, 50, 22, 0.75f, 0.8f);

        //For testing purposes add button to take to generic Lecture Room
        this.genericLectureRoom = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(this.genericLectureRoom, "Join lecture room", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 50f, 50f, 0.5f, 0.5f);
    }

    /**
     * @Method : groupListSetup: Sets up the recycleView list with all related groups a user can join
     */
    private void groupListSetup() {
        this.groupList = (RecyclerView) findViewById(R.id.groupList);
        this.groupLayout = new LinearLayoutManager(this.viewHelperClass.getActivityContext());
        this.groupList.setLayoutManager(this.groupLayout);
        //style the recycleViewer


        //Get all lecture groups
//        LectureGroupDAO lectureGroupDAO = new LectureGroupDAO();
//        this.groupListAdapter = new displayLectureGroupsAdapter(new ArrayList<>(Arrays.asList(lectureGroupDAO.getLectureGroupById("0"))));
//        this.groupList.setAdapter(this.groupListAdapter);

    }

    /**
     * @Method : modifyList: modifies the information in the group list based on the users query
     */
    public void modifyList(){

    }

    /**
     * @Method createLectureGroupListener : Verifies if user is a teacher and lets them create a Lecture Group
     */
    private void createLectureGroupListener() {
        createLectureGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getPermissionLevel() == 2){
                    startActivity(new Intent(lectureJoin.this, lectureCreate.class));
                } else {
                    Toast.makeText(viewHelperClass.getActivityContext(), "Error: only teachers may create lecture groups", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void genericLectureRoomListener() {
        genericLectureRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lectureJoin.this, lectureRoom.class);
                intent.putExtra("lectureGroup", lectureGroupDAO.getLectureGroupByFullName("ECE 155"));
                startActivity(intent);
            }
        });
    }
}
