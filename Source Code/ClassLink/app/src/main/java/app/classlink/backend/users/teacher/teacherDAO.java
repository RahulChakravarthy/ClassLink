package app.classlink.backend.users.teacher;

import app.classlink.backend.users.user.userDAO;

/**
 * @Class teacherDAO : DAO for teacher class individuals
 */
public class teacherDAO extends userDAO {
    /**
     * @param listName : Name of list
     * @Constructor: initializes the connection
     */
    public teacherDAO(String listName) {
        super(listName);
    }
}
