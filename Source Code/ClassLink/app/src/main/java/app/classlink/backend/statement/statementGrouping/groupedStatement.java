package app.classlink.backend.statement.statementGrouping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import app.classlink.backend.statement.statementType.answers;
import app.classlink.backend.statement.statementType.comments;
import app.classlink.backend.statement.statementType.question;

/**
 * @Class groupedStatement : stores a wrapped up statement (includes 1 question, all it's answers and all it's comments)
 */
public class groupedStatement {

    protected question statementQuestion;
    protected HashMap<answers, ArrayList<comments>> statementResponses; //store questions and answers in a hashmap since they are tightly related

    /**
     * @Method groupedStatement : is created every time a user asks a question
     * @param userQuestion : question object
     */
    public groupedStatement(question userQuestion) {
        this.statementQuestion = userQuestion;
    }

    public void addAnswer(answers userAnswer){
        statementResponses.put(userAnswer, null);
    }

    /**
     * @Method addComment : Adds a comment to a given solution
     * @param userAnswer : user answer that comment is being made
     * @param userComment : user comment that is being inserted/appended
     */
    public void addComment(answers userAnswer, comments userComment){
        if (!this.statementResponses.containsKey(userAnswer)) return; //if by chance answer key does not exist

        //Check to see if there were any previous comments, if not then add an arraylist of comments which can be appended
        if (this.statementResponses.get(userAnswer) == null){
            ArrayList<comments> newCommentChain = new ArrayList<>();
            newCommentChain.add(userComment);
            this.statementResponses.put(userAnswer, newCommentChain);
        } else {
            this.statementResponses.get(userAnswer).add(userComment);
        }
    }

    /**
     * @Method getStatementQuestion : returns the question of the entire grouped statement
     */
    public question getStatementQuestion(){
        return this.statementQuestion;
    }

    public HashMap<answers, ArrayList<comments>> getStatementResponses(){
        return this.statementResponses;
    }
}
