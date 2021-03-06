package app.classlink.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import app.classlink.R;
import app.classlink.backend.core.WorkerThread;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.groups.lecture.RefreshLectureGroupListHandler;
import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.users.user.user;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.activityParameters;
import app.classlink.backend.adapters.displayLectureGroupsAdapter;
import app.classlink.helperClasses.viewHelperClass;

public class lectureJoin extends baseActivity implements activityParameters {

    private ImageView createLectureGroup, line;
    private EditText searchBox;
    private RecyclerView groupList;
    private displayLectureGroupsAdapter groupListAdapter;
    private LinearLayoutManager groupLayout;
    private WorkerThread groupListThread;

    //DAOs
    private userDAO userDAO;
    private LectureGroupDAO lectureGroupDAO;

    //current user
    private user currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_lecture_join);

        layoutSetup();
        setActivityDAOListeners();
        groupListSetup(); //initialise group list display
        createLectureGroupListener();
        refreshGroupList(); //setup a thread that refreshes the list every so often
    }

    /**
     * @Method  onResume : verify user credentials and set the groupList thread if it hasnt already been set
     */
    @Override
    public void onResume(){
        super.onResume();
        if (!retrieveUser()){
            startActivity(new Intent(lectureJoin.this, login.class));
        }
        if (this.groupListThread == null){
            refreshGroupList();
        }
    }

    /**
     * @Method onPause : interrupt and stop the groupList thread if the user navigates away to save system resources
     */
    @Override
    public void onPause(){
        super.onPause();
        this.groupListThread.interrupt();
        this.groupListThread = null;
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        this.userDAO = new userDAO();
        this.userDAO.setCacheListener();
        this.currentUser = ((user) getIntent().getExtras().get("user")); //get the user passed in by the previous activity quickly
        this.lectureGroupDAO = new LectureGroupDAO();
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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Text based graphics
        this.viewHelperClass.addText("Join a Group", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        this.viewHelperClass.addText("Search for a group", "OpenSans-Regular", "BLACK", 2, 15, 50, 12);

        // Graphic based Graphics
        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(this.line, R.drawable.line, 50, 8,0.5f, 1, false);

        this.createLectureGroup = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(this.createLectureGroup, "Create Lecture Group", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 30f, 90f, 0.5f,0.5f);

        this.searchBox = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox(null, R.drawable.inputbox, this.searchBox, InputType.TYPE_CLASS_TEXT, 15, 50, 19, 0.75f, 0.8f);
    }

    /**
     * @Method groupListSetup: Sets up the recycleView list with all related groups a user can join
     */
    @SuppressWarnings("ALL")
    private void groupListSetup() {
        this.groupList = (RecyclerView) findViewById(R.id.groupList);

        //Display toast prompt in case lecture group list is not ready
        ArrayList<lectureGroup> allLectureGroups = (ArrayList<lectureGroup>) getIntent().getExtras().get("allLectureGroups");
        this.groupListAdapter = new displayLectureGroupsAdapter(allLectureGroups, currentUser);
        this.groupLayout = new LinearLayoutManager(this.viewHelperClass.getActivityContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(groupList.getContext(),groupLayout.getOrientation());
        this.groupList.addItemDecoration(dividerItemDecoration);

        this.groupList.setLayoutManager(this.groupLayout);
        this.groupList.setAdapter(this.groupListAdapter);
        this.groupListAdapter.notifyDataSetChanged();
    }

    private void refreshGroupList() {
        this.groupListThread = new RefreshLectureGroupListHandler(this, this.groupListAdapter, this.groupList, this.lectureGroupDAO);
        this.groupListThread.start();
    }

    /**
     * @Method : modifyList: modifies the information in the group list based on the users query (ADD THIS IN THE FUTURE)
     */
    public void modifyList(){}

    /**
     * @Method createLectureGroupListener : Verifies if user is a teacher and lets them create a Lecture Group
     */
    private void createLectureGroupListener() {
        createLectureGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getPermissionLevel() == 2){
                    //Pass teachers institution information in Intent to be used by lectureCreate
                    Intent intent = new Intent(lectureJoin.this, lectureCreate.class);
                    intent.putExtra("School", userDAO.getUserByEmail(userAuth.getCurrentUser().getEmail()).getSchool().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(viewHelperClass.getActivityContext(), "Error: only teachers may create lecture groups", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
