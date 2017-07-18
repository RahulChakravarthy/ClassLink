package app.classlink.backend.statement.statementType;

/**
 * @Class questions: A question class
 */
public class question extends statements {

    protected String questionText;
    protected final String statementType = "QUESTION";

    public question() {

    }

    public question(String questionText, int userId){
        this.questionText = questionText;
        this.userId = userId;
        this.score = 0;
    }

    public question(question q) {
        this.questionText = q.getQuestionText();
        this.userId = q.getUserId();
        this.score = q.getScore();

    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }

    public String getQuestionText() { return this.questionText; }
}
