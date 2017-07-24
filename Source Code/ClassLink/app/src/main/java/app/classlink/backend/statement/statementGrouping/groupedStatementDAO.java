package app.classlink.backend.statement.statementGrouping;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;

/**
 * @Class groupedStatementDAO : interface object that returns grouped statements
 */
public class groupedStatementDAO extends DAO {

    /**
     * @Constructor: initializes the connection
     */
    public groupedStatementDAO() {
        super(listNames.GROUPEDSTATEMENT);
    }
}
