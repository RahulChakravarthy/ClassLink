package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

public class lectureJoin extends baseActivity implements activityParameters {

    private ImageView createLectureGroup, line;
    private RecyclerView groupList;
    private LinearLayoutManager groupLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_lecture_join);

        layoutSetup();
        groupListSetup();
        createLectureGroupListener();

    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_join_lecture_group_menu);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);

        // Text based graphics
        this.viewHelperClass.addText("Join a Group", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        this.viewHelperClass.addText("Search for a group", "OpenSans-Regular", "BLACK", 2, 15, 50, 15);

        // Graphic based Graphics
        this.line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 8,0.5f, 1, false);

        this.createLectureGroup = new ImageView(getApplicationContext());
        this.viewHelperClass.addTextToButton(createLectureGroup, "Create Lecture Group", 15, "OpenSans-Regular", "BLACK", R.drawable.curvedbutton, 30f, 90f, 0.5f,0.5f);

    }

    /**
     * @Method : groupListSetup: Sets up the recycleView list with all related groups a user can join
     */
    private void groupListSetup() {
        this.groupList = (RecyclerView) findViewById(R.id.groupList);
        this.groupLayout = new LinearLayoutManager(this.viewHelperClass.getActivityContext());
        groupList.setLayoutManager(this.groupLayout);

    }

    /**
     * @Method createLectureGroupListener : Verifies if user is a teacher and lets them create a Lecture Group
     */
    private void createLectureGroupListener() {
        createLectureGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lectureJoin.this, lectureCreate.class));
            }
        });
    }
}
