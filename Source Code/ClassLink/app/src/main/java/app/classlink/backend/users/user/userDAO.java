package app.classlink.backend.users.user;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;

/**
 * @Class userDAO : DAO for user class individuals
 */
public class userDAO extends DAO {

    /**
     * @Constructor: initializes the connection
     */
    public userDAO() {
        super(listNames.USERS);
    }
}
