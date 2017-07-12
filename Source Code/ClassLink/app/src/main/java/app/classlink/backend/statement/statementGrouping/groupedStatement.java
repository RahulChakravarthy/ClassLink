package app.classlink.backend.statement.statementGrouping;

import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.comments;
import app.classlink.backend.statement.statementType.question;

/**
 * @Class groupedStatement : stores a wrapped up statement (includes 1 question, all it's answers and all it's comments)
 */
public class groupedStatement {

    protected question statementQuestion;
    //Figure out how to store answers and comments

    /**
     * @Method groupedStatement : is created every time a user asks a question
     * @param userQuestion : question object
     */
    public groupedStatement(question userQuestion) {
        this.statementQuestion = userQuestion;
    }

    public void addAnswer(answers userAnswer){
        //Store it in data structure
    }

    public void addComment(comments userComment){
        //Store it in data structure
    }

    /**
     * @Method getStatementQuestion : returns the question of the entire grouped statement
     */
    public question getStatementQuestion(){
        return this.statementQuestion;
    }

    /**
     * @Method getAllAnswers : returns an array of all answers to the question in chronological order
     */
    public answers[] getAllAnswers(){
        return null;
    }

    /**
     * @Method getAllComments : returns an array of all comments corresponding to the specific answer
     */
    public comments[] getAllComments(){
        return null;
    }
}
