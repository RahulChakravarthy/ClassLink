package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.backend.groups.lecture.lectureGroup;
import app.classlink.backend.users.user.user;
import app.classlink.helperClasses.activityParameters;
import app.classlink.backend.core.baseActivity;
import app.classlink.helperClasses.subViewHelperClass;
import app.classlink.helperClasses.viewHelperClass;

public class lectureRoom extends baseActivity
        implements NavigationView.OnNavigationItemSelectedListener, activityParameters {

    private ConstraintLayout backgroundLayout; //background activity layout
    private DrawerLayout navbarLayout; //navigation drawer handler
    private LinearLayout leftsideDrawerLayout; //left side drawer layout

    private subViewHelperClass subViewHelperClass; //viewhelperclass for various layouts

    private lectureGroup lectureGroup;
    private user currentUser;
    private EditText inputStatement;

    private LectureGroupDAO lectureGroupDAO;

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

        //non-core
        setActionBar();
        setFloatingActionBar();
//        syncStatements();


    }

    @Override
    public void onStart(){
        super.onStart();
        if (!retrieveUser()){
            startActivity(new Intent(lectureRoom.this, login.class));
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.lecture_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
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
        setTitle(this.lectureGroup.getGroupName() + ":  " + this.lectureGroup.getGroupDescription());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2A97DD")));

    }

    /**
     * @Method setFloatingActionBar : styles the floating action bar at the bottom of the activity (little circle)
     */
    private void setFloatingActionBar() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //get whether or not user has this group favourited, if so set it to the heart icon
        //fab.setImageResource();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Switch the image resource from between favoured and non favoured group
                //fab.setImageResource();
            }
        });
    }

    /**
     * Activity Methods
     */

    /**
     *@Method layoutSetup : sets up background layout
     */
    @Override
    public void layoutSetup() {
        this.subViewHelperClass = new subViewHelperClass(getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.backgroundLayout = (ConstraintLayout) findViewById(R.id.main_lecture_room_layout);

        this.backgroundLayout.setBackgroundColor(Color.parseColor("#3D87B5"));
        this.backgroundLayout.addView(this.subViewHelperClass.addText("Teacher Support Material\n           Coming Soon", "OpenSans-Bold", "BLACK", 2, 20, 50, 40));
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
        this.leftsideDrawerLayout = (LinearLayout) findViewById(R.id.side_drawer_layout);
        this.subViewHelperClass = new subViewHelperClass(getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        this.inputStatement = new EditText(getApplicationContext());

    }

    /**
     * @Method coreSetup : sets up all information for this activity including intents and DAOs
     */
    private void coreSetup() {
        //capture Intent values here and set the activity lecture group equal to the passed in group
        this.lectureGroup = (lectureGroup) getIntent().getExtras().get("lectureGroup");
        this.currentUser = (user) getIntent().getExtras().get("user");
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {
        //set lecture group cache listener
        this.lectureGroupDAO = new LectureGroupDAO();
        this.lectureGroupDAO.setCacheListener(this.currentUser.getSchool().toString());

        //set statement listener on said lecture group
    }

    /**
     * @Method syncStatements : timer thread that periodically syncs groupedStatement with database
     */
    public void syncStatements(){

    }

    /**
     * @Method loadLectureStatements : Loads groupedStatement from the specific lecture group into buffer to be displayed on the screen
     */
    private void loadLectureStatements() {
//        for (groupedStatement statement : this.lectureGroup.getGroupedStatement()){
//            displayStatements();
//        }
    }

    private void displayStatements() {
        //add all the code for displaying each statement in the side view bar
    }
}
