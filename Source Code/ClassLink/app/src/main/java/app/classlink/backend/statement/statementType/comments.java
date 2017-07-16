package app.classlink.backend.statement.statementType;

/**
 * @Class comments : made by users
 */
public class comments extends statements {

    protected String commentText;
    protected final String statementType = "COMMENTS";

    public comments(String commentText, int userId){
        this.commentText = commentText;
        this.userIdWhoIssuedStatement = userId;
        this.score = 0;
    }

    @Override
    public String getStatementType() {
        return this.statementType;
    }
}
