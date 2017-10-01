package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.misc.DateParser;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.statement.statementGrouping.groupedStatementDAO;
import app.classlink.backend.statement.statementType.question;
import app.classlink.backend.statement.threadHandler.RefreshStatementListHandler;
import app.classlink.backend.users.user.user;
import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.recyclerAdapters.displayStatementAdapter;
import app.classlink.helperClasses.viewHelperClass;

public class lectureRoom extends baseActivity
        implements NavigationView.OnNavigationItemSelectedListener, activityParameters {

    private ConstraintLayout backgroundLayout; //background activity layout
    private DrawerLayout navbarLayout; //navigation bar handler

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private displayStatementAdapter statementAdapter;

    private viewHelperClass viewHelperClass; //viewhelperclass for various layouts

    private lectureGroup currentLectureGroup;
    private user currentUser;

    private EditText inputText;
    private RefreshStatementListHandler refreshStatementListHandler;

    private LectureGroupDAO lectureGroupDAO;
    private groupedStatementDAO groupedStatementDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Call activity methods here
        //core order
        coreSetup();
        setActivityDAOListeners();
        layoutSetup();
        navbarLayoutSetup();
        sideDrawerLayoutSetup();

        //list view display
        listViewSetup();

        //non-core
        setActionBar();
        setFloatingActionBar();
        syncStatements();

    }

    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(lectureRoom.this, login.class));
        }
        if (this.refreshStatementListHandler == null){
            syncStatements();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onPause(){
        super.onPause();
        this.refreshStatementListHandler.interrupt();
        this.refreshStatementListHandler = null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * @Method onNavigationItemSelected : handles displaying and actions when groupedStatement are clicked
     * @param item : Displays all lecture groupedStatement
     * @return whether or not an item was pressed
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //display options to report question or to report user
        //if it is a teacher, they can report and even ban the user who posed it
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * @Method setActionBar : formats and styles action bar
     */
    private void setActionBar(){
        setTitle(this.currentLectureGroup.getGroupName() + ":  " + this.currentLectureGroup.getGroupDescription());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A97DD")));
    }

    /**
     * @Method setFloatingActionBar : styles the floating action bar at the bottom of the activity (little circle)
     */
    private void setFloatingActionBar() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //submit textbox contents
        fab.setImageResource(R.drawable.poststatement);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHelperClass.isEditTextEmpty(new ArrayList<>(Arrays.asList(inputText)))){
                    groupedStatementDAO.addStatement(new groupedStatement(new question(inputText.getText().toString().trim(), currentUser.getUserId())));
                    inputText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot post empty message as question", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Activity Methods
     */

    /**
     * @Method coreSetup : sets up all information for this activity including intents
     */
    private void coreSetup() {
        //capture Intent values here and set the activity lecture group equal to the passed in group
        this.currentLectureGroup = (lectureGroup) getIntent().getExtras().get("lectureGroup");
        this.currentUser = (user) getIntent().getExtras().get("user");
    }

    /**
     *@Method layoutSetup : sets up background layout
     */
    @Override
    public void layoutSetup() {
        //initialize background layout
        this.backgroundLayout = (ConstraintLayout) findViewById(R.id.main_lecture_room_layout);
        this.backgroundLayout.setBackgroundColor(Color.parseColor("#bdecfd"));
        //Initialising the constrained layout for all other views in the main activity
        this.viewHelperClass = new viewHelperClass(this.backgroundLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.inputText = new EditText(getApplicationContext());
        this.viewHelperClass.addGraphicInputBox("Ask an anonymous question...", R.drawable.inputbox, this.inputText, InputType.TYPE_CLASS_TEXT, 15, 40f, 81, 0.7f, 0.85f);
    }

    /**
     * @Method navbarLayoutSetup : sets up navigation bar layout
     */
    private void navbarLayoutSetup() {
        this.navbarLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    /**
     * @Method sideDrawerLayoutSetup : sets up side drawer layout
     */
    private void sideDrawerLayoutSetup() {
        //Display message that additional features and teacher content will be coming soon
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        //set lecture group cache listener
        this.lectureGroupDAO = new LectureGroupDAO();
        this.lectureGroupDAO.setCacheListener(this.currentUser.getSchool().toString());

        //set statement listener on said lecture group
        this.groupedStatementDAO = new groupedStatementDAO();
        this.groupedStatementDAO.setCacheListener(this.currentLectureGroup);
    }

    /**
     * @Method listViewSetup : setup for recycler list view
     */
    private void listViewSetup() {
        this.recyclerView = (RecyclerView) findViewById(R.id.statement_list_view);
        this.statementAdapter = new displayStatementAdapter(DateParser.getOrderedStatementsByDate(new LinkedList<>(this.currentLectureGroup.getGroupedStatement().values())), this.groupedStatementDAO, this.currentUser);
        this.linearLayoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.recyclerView.setAdapter(this.statementAdapter);
    }

    /**
     * @Method syncStatements : timer thread that periodically syncs groupedStatement with database
     */
    public void syncStatements(){
        this.refreshStatementListHandler = new RefreshStatementListHandler(this, this.statementAdapter, this.lectureGroupDAO, this.currentLectureGroup.getGroupName());
        this.refreshStatementListHandler.start();
    }
}
