package app.classlink.backend.statement.statementType;

/**
 * @Class comments : user comments on recoreded answers
 */
public class comments extends statements {

    protected String commentText;
    protected final String statementType = "COMMENTS";

    public comments(String commentText, String userId){
        this.commentText = commentText;
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
