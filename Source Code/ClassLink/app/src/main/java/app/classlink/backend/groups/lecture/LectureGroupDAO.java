package app.classlink.backend.groups.lecture;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Hashtable;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class LectureGroupDAO : DAO for accessing lecture groups
 */
public class LectureGroupDAO extends DAO {

    protected Hashtable<String, lectureGroup> cache; //key = id of group, value = lectureGroup object
    /**
     * @Consructor LectureGroupDAO
     */
    public LectureGroupDAO() {
        super(listNames.GROUPS);
        this.list = this.list.child(listNames.LECTUREGROUPS);
        this.cache = new Hashtable<>();
    }

    /**
     * @Method createLectureGroup programmatically creates a group in and stores it in the database
     * @param lectureGroupName : Name of the group
     * @param lectureGroupDescription : description of the group
     * @param lectureCreator : teacher who created the group
     * @param schoolName : School that this group belongs to
     * @return boolean : whether group was created or not
     */
    public boolean createLectureGroup(String lectureGroupName, String lectureGroupDescription, teacher lectureCreator, School schoolName){
        if (getLectureGroupByFullName(lectureGroupName) == null){
            String lectureGroupId = this.list.child(schoolName.toString()).push().getKey();
            lectureGroup newGroup = new lectureGroup(schoolName, lectureGroupName, lectureGroupId, lectureGroupDescription, lectureCreator);
            this.list.child(schoolName.toString()).child(lectureGroupId).setValue(newGroup);
            return true;
        } else {
            return false; //lecture group is not a duplicate
        }
    }

    /**
     * @Method createLectureGroup programmatically creates a group in and stores it in the database
     * @param lectureGroup : reference to lecture Group object
     */
    public boolean createLectureGroup(lectureGroup lectureGroup){
        String lectureGroupId = this.list.child(lectureGroup.getSchoolName().toString()).push().getKey();
        this.list.child(lectureGroup.getSchoolName().toString()).child(lectureGroupId).setValue(lectureGroup);
        return true; //lecture group is not a duplicate
    }

    /**
     * @Method getLectureGroupById : returns a lecture group by id
     * @param lectureGroupId : input lecture ID
     */
    public lectureGroup getLectureGroupById(String lectureGroupId) {
        return cache.get(lectureGroupId);
    }

    /**
     * @Method getLectureGroupByName : returns the lecture group with that name (only one since there shouldnt be a lecture group with the exact same name)
     * @param name : lecture group name
     * @return lectureGroup Array List
     */
    public lectureGroup getLectureGroupByFullName(String name){
        ArrayList<lectureGroup> temp = new ArrayList<>();
        for (lectureGroup child : cache.values()){
            if (child.getGroupName().equals(name)){
                temp.add(child);
            }
        }
        return temp.size() != 0? temp.get(0): null;
    }

    /**
     * @Method getLectureGroupBySubName : returns a lectureGroups that have subname in it as a substring
     * @param subName : the substring you want to query with
     * @return lectureGroup ArrayList
     */
    public ArrayList<lectureGroup> getLectureGroupBySubName(String subName){
        return  null;
    }

    /**
     * @Method getLectureGroupByTeacher : returns all lecture groups hosted by a provided teacher
     * @param teacherFirstName : teacher first name whom you wish to view all lecture groups
     * @param teacherLastName : teacher last name you wish to query groups by
     * @return lectureGroup ArrayList
     */
    public ArrayList<lectureGroup> getLectureByTeacherName(String teacherFirstName, String teacherLastName){
        ArrayList<lectureGroup> temp = new ArrayList<>();
        for (lectureGroup child : cache.values()){
            if (teacherFirstName != null && teacherLastName != null){ //query results with both first name and last name params
                if (child.getLectureCreator().getFirstName().equals(teacherFirstName) && child.getLectureCreator().getLastName().equals(teacherLastName)){
                    temp.add(child);
                }
            } else if (teacherFirstName != null){
                if (child.getLectureCreator().getFirstName().equals(teacherFirstName)){
                    temp.add(child);
                }
            } else if (teacherLastName != null){
                if (child.getLectureCreator().getLastName().equals(teacherLastName)){
                    temp.add(child);
                }
            } else {/* do nothing a name must be provided*/}
        }
        return temp;
    }

    /**
     * @Method getAllLectureGroups : retrieves all lecture groups (by the school that the listener was provided)
     */
    public ArrayList<lectureGroup> getAllLectureGroups(){
        return new ArrayList<>(cache.values());
    }

    /**
     * @Method deleteLectureGroup : deletes  a lecture group in the database by String id key
     * @param lectureGroup : Lecture Group Reference
     */
    public void deleteLectureGroup(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getSchoolName().toString()).child(lectureGroup.getGroupId()).removeValue();
    }

    /**
     * @Method deleteAllLectureGroups : deletes all lecture groups in the database (SHOULD NEVER BE CALLED, ONLY HERE FOR TESTING)
     */
    private void deleteAllLectureGroups(){
        this.list.removeValue();
    }

    /**
     * @Method updateLectureGroup : updates the entire lecture group
     */
    @Deprecated //This is a last resort method and should ideally not be called to avoid overhead
    public void updateLectureGroup(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getSchoolName().toString()).child(lectureGroup.getGroupId()).setValue(lectureGroup);
    }

    /**
     * @Method setCacheListener : sets the cache listener which will automatically update the cache with values which can be queried with the above method
     * @param schoolName : school name that the cache should listen to
     */
    public void setCacheListener(String schoolName){
        this.list.child(schoolName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(lectureGroup.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(lectureGroup.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cache.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(lectureGroup.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getDetails());
            }
        });
    }

}
