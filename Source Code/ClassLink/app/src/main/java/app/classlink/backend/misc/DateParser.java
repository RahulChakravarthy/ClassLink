package app.classlink.backend.misc;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import app.classlink.backend.statement.statementGrouping.groupedStatement;

/**
 * @Class DateParser : has static methods which help with date and time processing throughout the app
 */
public class DateParser {

    /**
     * @Method getOrderedStatementsByDate : orders statements by the date they were stored
     * @param statements : values are the statements themselves
     * @return list of statemnts in chronological order
     */
    @Nullable
    @SuppressWarnings("ALL")
    public static LinkedList<groupedStatement> getOrderedStatementsByDate(LinkedList<groupedStatement> statements){
        LinkedList<groupedStatement> organizedStatements = new LinkedList<>();
        parent : for (groupedStatement statement : statements){
            if (organizedStatements.size() == 0){
                organizedStatements.addFirst(statement);
            } else {
                DateFormat df = new SimpleDateFormat("yyMMddHHmmssZ");
                Date newStatementWrittenTime;
                try {
                    newStatementWrittenTime = df.parse(statement.getStatementQuestion().getWrittenTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }

                //do a check to see if new statement must be inserted at the end of the list
                Date lastStatementWrittenTime;
                try {
                    lastStatementWrittenTime = df.parse(organizedStatements.getLast().getStatementQuestion().getWrittenTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
                if (newStatementWrittenTime.after(lastStatementWrittenTime)){
                    organizedStatements.addLast(statement);
                    continue;
                }

                //otherwise check where in the linked list this statement has to go
                child :for (int i = 0; i < organizedStatements.size(); i++){
                    Date oldStatementWrittenTime;
                    try {
                        oldStatementWrittenTime = df.parse(organizedStatements.get(i).getStatementQuestion().getWrittenTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                    //compare dates of both these statements
                    if (newStatementWrittenTime.before(oldStatementWrittenTime)){
                        organizedStatements.add(i, statement);
                        continue parent;
                    }
                }
            }
        }
        return organizedStatements;
    }
}
