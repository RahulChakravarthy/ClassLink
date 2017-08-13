package app.classlink.backend.statement.statementType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Class groupedStatement: wrapper class for the question, classes
 */
abstract public class statements {
    protected String userId;
    protected String statementId;
    protected int score;
    protected String writtenTime;

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

    public void setWrittenTime(){
        DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
        this.writtenTime = df.format(Calendar.getInstance().getTime());
    }
}
