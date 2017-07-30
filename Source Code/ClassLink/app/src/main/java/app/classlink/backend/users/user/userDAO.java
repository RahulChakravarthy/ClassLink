package app.classlink.backend.users.user;

import java.util.ArrayList;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;

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

    /**
     * @Method getUsers: Returns users based on input query
     * @return ArrayList of users matching the query
     */
    public ArrayList<user> getUsers(String userId, String userFirstName, String userLastName, String userEmail, School school){
        return null;
    }
}
