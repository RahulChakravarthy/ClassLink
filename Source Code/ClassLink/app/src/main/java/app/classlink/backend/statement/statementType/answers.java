package app.classlink.backend.statement.statementType;

/**
 * @Class
 */
public class answers extends statements {
    protected String answerText;
    protected final String statementType = "ANSWER";

    public answers(String answerText, int userId){
        this.answerText = answerText;
        this.userIdWhoIssuedStatement = userId;
        this.score = 0;
    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }
}
