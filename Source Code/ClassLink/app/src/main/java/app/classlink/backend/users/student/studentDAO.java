package app.classlink.backend.users.student;

import app.classlink.backend.users.user.userDAO;

/**
 * @Class studentDAO : DAO for student class individuals
 */
public class studentDAO extends userDAO {
    /**
     * @param listName : Name of list
     * @Constructor: initializes the connection
     */
    public studentDAO(String listName) {
        super(listName);
    }

}
