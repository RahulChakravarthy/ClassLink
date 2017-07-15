package app.classlink.backend.utility;

/**
 * Created by jaywe on 2017-06-09.
 */

public class Question {
    private int id;
    private String field;
    public int votes;

    public Question() {

    }

    public Question(int id, String question) {
        this.id = id;
        this.field = question;
    }

    public Question(Question question) {
        this.id = question.id;
        this.field = question.field;
        this.votes = question.votes;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String question) {
        this.field = question;
    }

    public void vote(int upOrDown) {
        if (upOrDown == 1) {
            this.votes++;
        } else {
            this.votes--;
        }
    }

    public void resetVotes() {
        this.votes = 0;
    }
}
