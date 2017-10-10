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

    /**
     * @Consructor: creates an instance of the RefreshStatementListHandler
     * @param currentActivity : activity that this thread is working on
     * @param statementAdapter : statement adapter
     * @param lectureGroupDAO : lectureGroupDAO
     * @param nameOfGroup : Name of lecture group that we are listening to
     */
    public RefreshStatementListHandler(Activity currentActivity, displayStatementAdapter statementAdapter, LectureGroupDAO lectureGroupDAO, String nameOfGroup){
        this.currentActivity = currentActivity;
        this.statementAdapter = statementAdapter;
        this.lectureGroupDAO = lectureGroupDAO;
        this.nameOfGroup = nameOfGroup;
    }

    /**
     * @Method run : instructions that will execute everytime thread is run
     */
    @Override
    public void run(){
        try {
            while (!isInterrupted()) {
                this.currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("REFRESH STATEMENT LIST", "REFRESHING");
                        statementAdapter.swapData(new LinkedList<>(lectureGroupDAO.getLectureGroupByFullName(nameOfGroup).getGroupedStatement().values()));
                    }
                });
                Thread.sleep(3000);
            }
        } catch (InterruptedException ignored) {
            Log.d("INTERRUPTION", "THREAD HAS STOPPED");
        }
    }
}
