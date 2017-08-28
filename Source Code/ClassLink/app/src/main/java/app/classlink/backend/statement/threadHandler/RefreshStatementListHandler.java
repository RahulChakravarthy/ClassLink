package app.classlink.backend.statement.threadHandler;

import android.app.Activity;
import android.util.Log;
import java.util.LinkedList;
import app.classlink.backend.core.WorkerThread;
import app.classlink.backend.groups.lecture.LectureGroupDAO;
import app.classlink.helperClasses.recyclerAdapters.displayStatementAdapter;

/**
 * @Class RefreshStatementListHandler : handles run operation of statement refreshing
 */
public class RefreshStatementListHandler extends WorkerThread {

    private Activity currentActivity;
    private displayStatementAdapter statementAdapter;
    private LectureGroupDAO lectureGroupDAO;
    private String nameOfGroup;

    public RefreshStatementListHandler(Activity currentActivity, displayStatementAdapter statementAdapter, LectureGroupDAO lectureGroupDAO, String nameOfGroup){
        this.currentActivity = currentActivity;
        this.statementAdapter = statementAdapter;
        this.lectureGroupDAO = lectureGroupDAO;
        this.nameOfGroup = nameOfGroup;
    }

    @Override
    public void run(){
        try {
            while (!isInterrupted()) {
                Thread.sleep(5000);
                this.currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("REFRESH STATEMENT LIST", "REFRESHING");
                        statementAdapter.swapData(new LinkedList<>(lectureGroupDAO.getLectureGroupByFullName(nameOfGroup).getGroupedStatement().values()));
                    }
                });
            }
        } catch (InterruptedException ignored) {
            Log.d("INTERRUPTION", "THREAD HAS STOPPED");
        }
    }
}
