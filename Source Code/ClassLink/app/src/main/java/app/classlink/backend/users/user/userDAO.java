package app.classlink.backend.users.user;

import app.classlink.backend.core.DAO;

/**
 * @Class userDAO : DAO for user class individuals
 */
public class userDAO extends DAO{

    /**
     * @param listName : Name of list
     * @Constructor: initializes the connection
     */
    public userDAO(String listName) {
        super(listName);
    }
}
