package app.classlink.backend.statement.statementType;

/**
 * @Class answers : Answers to questions
 */
public class answers extends statements {
    protected String answerText;
    protected final String statementType = "ANSWER";

    public answers(String answerText, String userId){
        this.answerText = answerText;
        this.userId = userId;
        this.score = 0;
        this.setWrittenTime();
        this.startUserEmailsWhoUpVoted();
    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }
}
