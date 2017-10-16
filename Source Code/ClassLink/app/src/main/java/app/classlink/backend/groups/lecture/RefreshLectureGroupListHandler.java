package app.classlink.backend.groups.lecture;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import app.classlink.backend.core.WorkerThread;
import app.classlink.backend.adapters.displayLectureGroupsAdapter;


/**
 * @Class RefreshLectureGroupListHandler : handles run operation of group refreshing
 */
public class RefreshLectureGroupListHandler extends WorkerThread implements Runnable {

    private Activity currentActivity;
    private displayLectureGroupsAdapter groupAdapter;
    private LectureGroupDAO lectureGroupDAO;
    private RecyclerView groupList;

    /**
     * @Consructor: creates an instance of the RefreshLectureGroupList thread that listens to updates in the remote servers
     * @param currentActivity : current Activity that this thread will be activated in
     * @param groupAdpater : lecture group adapated containing all the current lecture groups
     * @param groupList : Recycler that will be updated with values from group list
     * @param lectureGroupDAO : lecture group DAO which will fetch updated lists from servers to compare if any need to be added to the current list
     */
    public RefreshLectureGroupListHandler(Activity currentActivity, displayLectureGroupsAdapter groupAdpater, RecyclerView groupList, LectureGroupDAO lectureGroupDAO) {
        this.currentActivity = currentActivity;
        this.groupAdapter = groupAdpater;
        this.lectureGroupDAO = lectureGroupDAO;
        this.groupList = groupList;
    }

    /**
     * @Method run : Instructions in this method will be executed everytime the thread loops
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                this.currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("REFRESHING GROUP LIST", "REFRESHED");
                        groupAdapter.swapData(new ArrayList<>(lectureGroupDAO.getAllLectureGroups()));
                    }
                });
                Thread.sleep(5000); //Thread loops every 5 seconds
            }
        } catch (InterruptedException ignored) {
            Log.d("INTERRUPTION", "THREAD HAS STOPPED");
        }
    }

}
