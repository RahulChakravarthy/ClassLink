package app.classlink.backend.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import app.classlink.backend.statement.statementGrouping.groupedStatement;

/**
 * @Class DateParser : has static methods which help with date and time processing throughout the app
 */
public class DateParser {

    /**
     * @Method getOrderedStatementsByDate : orders statements by the date they were stored
     * @param statements : keys are the dates, values are the statements themselves
     * @return list of statemnts in chronological order
     */
    public static LinkedList<groupedStatement> getOrderedStatementsByDate(HashMap<String, groupedStatement> statements){
        LinkedList<groupedStatement> organizedStatements = new LinkedList<>();
        for (String time : statements.keySet()){
            if (organizedStatements.size() == 0){
                //if its the first element in the list
                organizedStatements.addFirst(statements.get(time));
            } else if (parseStatementWrittenTime(statements.get(time), organizedStatements.getLast())) {
                //check to make sure it shouldn't be inserted at the end of the list
                organizedStatements.addLast(statements.get(time));
            } else {
                //otherwise parse the date and find out where it should go in the list
                for (int i = 0; i < organizedStatements.size(); i++){
                    if (parseStatementWrittenTime(organizedStatements.get(i), statements.get(time))){
                        organizedStatements.add(i, statements.get(time));
                    }
                }
            }
        }
        return organizedStatements;
    }

    /**
     * @Method parseStatementWrittenTime : get 2 statements and determine if the incoming statement needs to go before the current statement (i - 1) position
     * @Note: Method organizes both statements chronologically
     */
    private static boolean parseStatementWrittenTime(groupedStatement oldStatement, groupedStatement newStatement){
        int oldStatementYear = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(0,2));
        int newStatementYear =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(0,2));

        int oldStatementMonth = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(2,4));
        int newStatementMonth =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(2,4));

        int oldStatementDay = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(4,6));
        int newStatementDay =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(4,6));

        int oldStatementHour = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(6,8));
        int newStatementHour =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(6,8));

        int oldStatementMinute = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(8,10));
        int newStatementMinute =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(8,10));

        int oldStatementSecond = Integer.parseInt(oldStatement.getStatementQuestion().getWrittenTime().substring(10,12));
        int newStatementSecond =  Integer.parseInt(newStatement.getStatementQuestion().getWrittenTime().substring(10,12));

        //Monster if statement to check whether or not
        if (oldStatementYear == newStatementYear){
            if (oldStatementMonth == newStatementMonth){
                if (oldStatementDay == newStatementDay){
                    if (oldStatementHour == newStatementHour){
                        if (oldStatementMinute == newStatementMinute){
                            return oldStatementSecond > newStatementSecond;
                        }
                        return oldStatementMinute > newStatementMinute;
                    }
                    return oldStatementHour > newStatementHour;
                }
                return oldStatementDay > newStatementDay;
            }
            return oldStatementMonth > newStatementMonth;
        }
        return oldStatementYear > newStatementYear;
    }
}
