package app.classlink.backend.groups.lecture;

import app.classlink.backend.core.DAO;

/**
 * @Class LectureGroupDAO : DAO for accessing lecture groups
 */
public class LectureGroupDAO extends DAO {

    public LectureGroupDAO(){}

    public lectureGroup[] getAllLectureGroups(){
        return new lectureGroup[4]; //return all lecture groups
    }
}
