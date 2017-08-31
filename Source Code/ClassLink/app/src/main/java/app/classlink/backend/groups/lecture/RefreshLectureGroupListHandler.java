package app.classlink.backend.groups.lecture;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import app.classlink.helperClasses.recyclerAdapters.displayLectureGroupsAdapter;
import app.classlink.backend.core.WorkerThread;


/**
 * @Class RefreshLectureGroupListHandler : handles run operation of group refreshing
 */
public class RefreshLectureGroupListHandler extends WorkerThread implements Runnable {

    private Activity currentActivity;
    private displayLectureGroupsAdapter groupAdapter;
    private LectureGroupDAO lectureGroupDAO;
    private RecyclerView groupList;

    public RefreshLectureGroupListHandler(Activity currentActivity, displayLectureGroupsAdapter groupAdpater, RecyclerView groupList, LectureGroupDAO lectureGroupDAO) {
        this.currentActivity = currentActivity;
        this.groupAdapter = groupAdpater;
        this.lectureGroupDAO = lectureGroupDAO;
        this.groupList = groupList;
    }

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
                Thread.sleep(5000);
            }
        } catch (InterruptedException ignored) {
            Log.d("INTERRUPTION", "THREAD HAS STOPPED");
        }
    }

}
