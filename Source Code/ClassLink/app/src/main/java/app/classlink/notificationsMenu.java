package app.classlink;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import app.classlink.backend.studyGroup;
import app.classlink.recyclerHelperClasses.groupAdapter;
import app.classlink.helperClasses.activityParameters;

import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

import app.classlink.backend.*;

/**
 * Created by jaywe on 2017-06-11.
 */

public class notificationsMenu extends baseActivity implements activityParameters {

    private RecyclerView notifications;
    private List<studyGroup> notificationList = new ArrayList<>();
    private groupAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_notifications);
        viewHelperClass = new viewHelperClass(activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
        buttonSetup();
        exampleNotifications();
    }

    /**
     * @Method layoutSetup : setting up all static components of the UI
     */

    @Override
    public void layoutSetup() {

        this.activityLayout.setBackgroundResource(R.drawable.bg);

        notifications = (RecyclerView) findViewById(R.id.notificationsView);
        gAdapter = new groupAdapter(notificationList);
        RecyclerView.LayoutManager gLayoutManager = new LinearLayoutManager(getApplicationContext());
        notifications.setLayoutManager(gLayoutManager);
        notifications.setItemAnimator(new DefaultItemAnimator());
        notifications.setAdapter(gAdapter);
    }

    /**
     * @Method buttonSetup : sets up all button listeners for the activity
     */
    public void buttonSetup() {
        notifications.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int actions;       //onInterceptTouchEvent fires once onPress and once again onRelease so this is a quick fix to only fire onClick once for these two actions... also sometimes on scroll it will increment as well
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                actions++;
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && actions >= 2) {
                    onClick(child, rv.getChildPosition(child));
                    actions = 0;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            public void onClick(View view, int position) {
                studyGroup studyGroup = notificationList.get(position);
                Log.d("Testing onClick", "description: " + studyGroup.getGroupDescription());

                notificationList.remove(position);
                gAdapter.notifyItemRemoved(position);
            }
        });
    }

    /**
    *  @Method exampleNotifications : set up some example notifications to populate the recyclerview
     **/
    public void exampleNotifications() {
        studyGroup ex;

        for (int i = 0; i < 10; i++) {
            ex = new studyGroup(GROUP_TYPE.STUDY_GROUP, i, "example notification " + i, "notification text " + i);
            notificationList.add(ex);
        }
    }
}