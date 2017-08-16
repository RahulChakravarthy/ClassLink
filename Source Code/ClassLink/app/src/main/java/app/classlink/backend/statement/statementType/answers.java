package app.classlink.backend.statement.statementType;

/**
 * @Class
 */
public class answers extends statements {
    protected String answerText;
    protected final String statementType = "ANSWER";

    public answers(String answerText, String userId){
        this.answerText = answerText;
        this.userId = userId;
        this.score = 0;
        this.setWrittenTime();
    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }
}
