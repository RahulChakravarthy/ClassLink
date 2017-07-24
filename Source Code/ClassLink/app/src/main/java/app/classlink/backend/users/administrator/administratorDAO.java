package app.classlink.backend.users.administrator;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;

/**
 * @Class administratorDAO : DAO for the administrator class individuals
 */
public class administratorDAO extends DAO {
    /**
     * @Constructor: initializes the connection
     */
    public administratorDAO() {
        super(listNames.ADMIN);
    }
}
