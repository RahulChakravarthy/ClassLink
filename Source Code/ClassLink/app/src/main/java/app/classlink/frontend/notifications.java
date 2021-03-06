package app.classlink.frontend;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.backend.groups.study.studyGroup;
import app.classlink.helperClasses.activityParameters;
import app.classlink.backend.adapters.groupAdapter;
import app.classlink.helperClasses.viewHelperClass;

public class notifications extends baseActivity implements activityParameters {

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
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {

    }

    /**
     * @Method layoutSetup : setting up all static components of the UI
     */
    @Override
    public void layoutSetup() {

        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);
        this.viewHelperClass.addText("Notifications", "OpenSans-ExtraBold", "BLACK", 2, 25, 50, 5);
        ImageView line = new ImageView(getApplicationContext());
        this.viewHelperClass.addGraphics(line, R.drawable.line, 50, 8, 0.85f, 1, false);
        this.viewHelperClass.addText("This feature is coming soon", "OpenSans-Semibold", "BLACK", 2, 25, 50, 15);

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
//            ex = new studyGroup(GROUP_TYPE.STUDYGROUPS, Integer.toString(i), "example notification " + i, "notification text " + i);
//            notificationList.add(ex);
        }
    }
}
