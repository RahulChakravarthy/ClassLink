package app.classlink.backend.groups.lecture;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class LectureGroupDAO : DAO for accessing lecture groups
 */
public class LectureGroupDAO extends DAO {

    /**
     * @Consructor LectureGroupDAO
     */
    public LectureGroupDAO(){
        super(listNames.LECTUREGROUP);
    }

    /**
     * @Method  startList : Method handler for adding changing and deleting data from firebase
     * @return : returns the list it was modifying
     */
    public DatabaseReference startList(){
        this.list.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("WARNING", "ACTION WAS CANCELLED");
            }
        });
        return this.list;
    }

    /**
     * @Method createLectureGroup programmatically creates a group in and stores it in the database
     * @param lectureGroupName : Name of the group
     * @param lectureGroupDescription : description of the group
     * @param lectureCreator : teacher who created the group
     * @param schoolName : School that this group belongs to
     */
    public boolean createLectureGroup(String lectureGroupName, String lectureGroupDescription, teacher lectureCreator, School schoolName){
        //add a check here to make sure the same group doesn't already exist
        String lectureGroupId = this.list.push().getKey();
        lectureGroup newGroup = new lectureGroup(schoolName, lectureGroupName, lectureGroupId, lectureGroupDescription, lectureCreator);
        this.list.child(lectureGroupId).setValue(newGroup);
        return true;
    }

    /**
     * @Method getAllLectureGroups : returns all the lecture groups stored in the database
     * @return array of all the lecture groups
     */
    public lectureGroup[] getAllLectureGroups(){
        return new lectureGroup[4]; //return all lecture groups
    }

    /**
     * @Method getLectureGroupById : returns a lecture group by id
     * @param lectureGroupId : input lecture ID
     * @return object lectureGroup
     */
    public lectureGroup getLectureGroupById(String lectureGroupId){
        return  null;
    }

    /**
     * @Method getLectureGroupByName : returns the lecture group with that name (only one since there shouldnt be a lecture group with the exact same name)
     */
//    public lectureGroup getLectureGroupByName(String name){
//
//    }

    /**
     * @Method getLectureGroupByTeacher : returns all lecture groups hosted by a provided teacher
     * @param userTeacher : teacher whom you wish to view all lecture groups
     */
//    public lectureGroup[] getLectureByTeacher(teacher userTeacher){
//    }

    /**
     * @Method getLectureByLectureTag : return all lecture groups with matching stored tags
     * @param tagValue : search for this value
     * @param tagName : search this this tag name
     */
//    public lectureGroup[] getLectureByLectureTag(String tagValue, String tagName){
//
//    }

    /**
     * @Method getLecture : returns a list of lecture groups based on information provided (THIS METHOD IS GONNA BE FUCKING HARD GET HELP)
     * @param groupName :
     */

    /**
     * @Method deleteLectureGroupByName : deletes a lectureGroup by name
     */
    public void deleteLectureGroupByName(String name){
        //get lecture group with that name
        //call deleteLectureGroupById
    }

    /**
     * @Method deleteLectureGroupById : deletes  a lecture group in the database by String id key
     * @param lectureGroupId : id key of group you want to delete
     */
    public void deleteLectureGroupById(String lectureGroupId){
        this.list.child(lectureGroupId).removeValue();
    }

    /**
     * @Method deleteAllLectureGroups : deletes all lecture groups in the database
     */
    public void deleteAllLectureGroups(){
        this.list.removeValue();
    }

    /**
     * @Method updateLectureGroup : updates the entire lecture group
     */
    public void updateLectureGroup(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getGroupId()).setValue(lectureGroup);
    }

}
