package app.classlink.backend.statement.statementGrouping;


import java.io.Serializable;

import app.classlink.backend.statement.statementType.question;

/**
 * @Note : In the future, implement a way of adding answers and comments to those answers (add those fields here)
 */

/**
 * @Class groupedStatement : stores a wrapped up statement (includes 1 question, all it's answers and all it's comments)
 */
public class groupedStatement implements Serializable {

    protected question statementQuestion;

    /**@Consructor
     * @Method groupedStatement : is created every time a user asks a question
     * @param userQuestion : question object
     */
    public groupedStatement(question userQuestion) {
        this.statementQuestion = userQuestion;
    }

    /**
     * @Consructor DATASNAPSHOT CONSTRUCTOR : DO NOT USE FOR REGULAR OBJECT CREATION
     */
    public groupedStatement(){
        //ONLY USED BY DATA SNAPSHOT
    }

    /**
     * @Method getStatementQuestion : returns the question of the entire grouped statement
     */
    public question getStatementQuestion(){
        return this.statementQuestion;
    }

}
