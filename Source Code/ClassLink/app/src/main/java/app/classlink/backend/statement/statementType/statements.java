package app.classlink.backend.statement.statementType;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Class groupedStatement: wrapper class for the question, classes
 */
abstract public class statements implements Serializable {
    protected String userId;
    protected String statementId;
    protected int score;
    protected String writtenTime;
    protected ArrayList<String> userEmailsWhoUpVoted;

    abstract public String getStatementType();

    public String getStatementId(){
        return this.statementId;
    }

    public String getUserId(){
        return this.userId;
    }

    public String getWrittenTime(){
        return this.writtenTime;
    }

    public int getScore() { return this.score; }

    public void setScore(int score) {this.score = score;}

    public ArrayList<String> getUserEmailsWhoUpVoted(){
        return this.userEmailsWhoUpVoted;
    }

    public void setUserEmailsWhoUpVoted(ArrayList<String> userEmailsWhoUpVoted){this.userEmailsWhoUpVoted = userEmailsWhoUpVoted;}

    public void startUserEmailsWhoUpVoted(){
        this.userEmailsWhoUpVoted = new ArrayList<>();
        this.userEmailsWhoUpVoted.add("NULL");
    }

    public void setWrittenTime(){
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        this.writtenTime = df.format(Calendar.getInstance().getTime());
    }
}
