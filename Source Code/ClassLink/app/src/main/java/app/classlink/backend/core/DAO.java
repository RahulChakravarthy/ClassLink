package app.classlink.backend.core;

/**
 * @Class  DAO : base DAO class for handling data movement
 */

public class DAO extends firebaseHelper {
    /**
     * @param listName : Name of list
     * @Constructor: initializes the connection
     */
    public DAO(String listName) {
        super(listName);
    }
}
