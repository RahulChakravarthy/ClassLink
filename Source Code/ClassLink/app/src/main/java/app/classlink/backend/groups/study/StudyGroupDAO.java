package app.classlink.backend.groups.study;

import app.classlink.backend.core.DAO;


/**
 * @Class StudyGroupDAO :
 */
public class StudyGroupDAO extends DAO {

    /**
     * @param listName : Name of list
     * @Constructor: initializes the connection
     */
    public StudyGroupDAO(String listName) {
        super(listName);
    }
}
