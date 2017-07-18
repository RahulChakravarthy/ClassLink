package app.classlink.backend.statement.statementType;

/**
 * @Class statements: wrapper class for the question, classes
 */
abstract public class statements {
    protected int userId;
    protected int statementId;
    protected int score;
    protected String writtenTime;

    abstract public String getStatementType();

    public int getStatementId(){
        return this.statementId;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getWrittenTime(){
        return this.writtenTime;
    }

    public int getScore() { return this.score; }
}
