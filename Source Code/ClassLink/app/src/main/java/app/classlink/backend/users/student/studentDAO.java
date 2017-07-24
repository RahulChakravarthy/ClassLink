package app.classlink.backend.users.student;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;

/**
 * @Class studentDAO : DAO for student class individuals
 */
public class studentDAO extends DAO {
    /**
     * @Constructor: initializes the connection
     */
    public studentDAO() {
        super(listNames.STUDENTS);
    }

}
