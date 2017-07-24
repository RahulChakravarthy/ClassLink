package app.classlink.backend.users.teacher;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;

/**
 * @Class teacherDAO : DAO for teacher class individuals
 */
public class teacherDAO extends DAO {
    /**
     * @Constructor: initializes the connection
     */
    public teacherDAO() {
        super(listNames.TEACHERS);
    }
}
