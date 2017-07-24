package app.classlink.backend.groups.study;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;


/**
 * @Class StudyGroupDAO :
 */
public class StudyGroupDAO extends DAO {

    /**
     * @Constructor: initializes the connection
     */
    public StudyGroupDAO() {
        super(listNames.STUDYGROUP);
    }
}
