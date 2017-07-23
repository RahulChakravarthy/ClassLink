package app.classlink.backend.groups.lecture;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.GROUP_TYPE;
import app.classlink.backend.core.listNames;
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
    @Override
    public DatabaseReference startList(){
        this.list.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addItem(dataSnapshot.getValue(lectureGroup.class));
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
        return new lectureGroup(GROUP_TYPE.LECTURE, "TEST", lectureGroupId, "THIS IS A TEST", null);
    }

    /**
     * @Method createLectureGroup programmatically creates a group in and stores it in the database
     * @param lectureGroupName : Name of the group
     * @param  lectureGroupDescription : description of the group
     */
    public void createLectureGroup(String lectureGroupName, String lectureGroupDescription, teacher lectureCreator){
        String lectureGroupId = this.list.push().getKey();
        lectureGroup newGroup = new lectureGroup(GROUP_TYPE.LECTURE,lectureGroupName, lectureGroupId, lectureGroupDescription, lectureCreator);
        this.list.child(lectureGroupId).setValue(newGroup);
    }

    /**
     * @Method deleteLectureGroupById : deletes  a lecture group in the database by String id key
     * @param lectureGroupId : id key of group you want to delete
     */
    public void deleteLectureGroupById(String lectureGroupId){
    }

    /**
     * @Method modifyLectureGroupSettingsById : Get's a lectureGroup from the database, modifies it's settings, then pushes it back to the database
     * @param lectureGroupId : id key of the group you want to modify
     */
    public void modifyLectureGroupSettingsById(String lectureGroupId){

    }

    /**
     * @Method updateLectureGroup : updates the entire lecture group after a question has been added to the database
     */
    public void updateLectureGroup(lectureGroup lectureGroup){

    }
}
