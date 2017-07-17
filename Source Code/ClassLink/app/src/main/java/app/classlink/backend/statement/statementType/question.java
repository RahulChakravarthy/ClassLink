package app.classlink.backend.statement.statementType;

/**
 * @Class questions: A question class
 */
public class question extends statements {

    protected String questionText;
    protected final String statementType = "QUESTION";

    public question(String questionText, int userId){
        this.questionText = questionText;
        this.userIdWhoIssuedStatement = userId;
        this.score = 0;
    }

    public question(question q) {
        this.questionText = q.getField();

    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }

    public String getField() { return this.questionText; }
}
