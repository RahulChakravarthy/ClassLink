package app.classlink.backend.groups.lecture;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.statement.statementGrouping.groupedStatement;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class LectureGroupDAO : DAO for accessing lecture groups
 */
public class LectureGroupDAO extends DAO {

    protected LinkedList<groupedStatement> groupedStatementsCache; //this caches all the changes and can be periodically called to update lecture statements

    /**
     * @Consructor LectureGroupDAO
     */
    public LectureGroupDAO(){
        super(listNames.GROUPS);
        this.list = this.list.child(listNames.LECTUREGROUPS);
        this.groupedStatementsCache = new LinkedList<>();
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
        if (this.getLectureGroup(null, lectureGroupName, lectureCreator.getFirstName(), lectureCreator.getLastName(), schoolName).size() == 0){
            String lectureGroupId = this.list.push().getKey();
            lectureGroup newGroup = new lectureGroup(schoolName, lectureGroupName, lectureGroupId, lectureGroupDescription, lectureCreator);
            this.list.child(lectureGroupId).setValue(newGroup);
            return true; //lecture group is not a duplicate
        }
        return false;//it is a duplicate throw a an error
    }

    /**
     * @Method createLectureGroup programmatically creates a group in and stores it in the database
     * @param lectureGroup : reference to lecture Group object
     */
    public boolean createLectureGroup(lectureGroup lectureGroup){
        if (this.getLectureGroup(lectureGroup.getGroupId(), lectureGroup.getGroupName(),lectureGroup.getLectureCreator().getFirstName(), lectureGroup.getLectureCreator().getLastName(), lectureGroup.getLectureCreator().getSchool()).size() == 0){
            String lectureGroupId = this.list.push().getKey();
            this.list.child(lectureGroupId).setValue(lectureGroup);
            return true; //lecture group is not a duplicate
        }
        return false;
    }

    /**
     * @Method getLectureGroupById : returns a lecture group by id
     * @param lectureGroupId : input lecture ID
     * @return object lectureGroup
     */
    public lectureGroup getLectureGroupById(String lectureGroupId){
        return this.getLectureGroup(lectureGroupId, null, null, null, null).get(0);
    }

    /**
     * @Method getLectureGroupByName : returns the lecture group with that name (only one since there shouldnt be a lecture group with the exact same name)
     */
    public lectureGroup getLectureGroupByName(String name, School school){
        return this.getLectureGroup(null, name, null, null, school).get(0);
    }

    /**
     * @Method getLectureGroupByTeacher : returns all lecture groups hosted by a provided teacher
     * @param userTeacher : teacher whom you wish to view all lecture groups
     */
    public ArrayList<lectureGroup> getLectureByTeacher(teacher userTeacher){
        return this.getLectureGroup(null, null, userTeacher.getFirstName(), userTeacher.getLastName(), userTeacher.getSchool());
    }

    /**
     * @Method getLectureByLectureTag : return all lecture groups with matching stored tags
     * @param tagValue : search for this value
     * @param tagName : search this this tag name'
     * NOTE: EVERYTIME YOU WANT TO ADD ANOTHER METHOD OF QUERY, IT MUST BE ALSO INCLUDED IN THE SWITCH STATEMENT
     */
    public ArrayList<lectureGroup> getLectureByLectureTag(String tagValue, String tagName, School school){
        switch (tagName){
            case "groupName":
                return this.getLectureGroup(null, tagValue, null, null, school);
            case "teacherFirstName":
                return this.getLectureGroup(null, null, tagValue, null, school);
            case "teacherLastName":
                return this.getLectureGroup(null, null, null, tagValue, school);
            default:
                return null;
        }
    }


    /**
     * @Method getLectureGroup : gets a lecture group by specified inputs
     * @param lectureGroupId : String lecture group id
     * @param lectureGroupName : String lecture group name
     * @param teacherFirstName : String teacher first name who created the group
     * @param teacherLastName : String teacher last name who created the group
     * @param School : School the group is part of
     * @return Array of Lecture groups Matching the desired query
     */
    @SuppressWarnings("WeakerAccess")
    public ArrayList<lectureGroup> getLectureGroup(String lectureGroupId, String lectureGroupName, String teacherFirstName, String teacherLastName, School School){
        final ArrayList<lectureGroup> tempList1 = new ArrayList<>();

        if (lectureGroupId != null){
            Query groupIdQuery = this.list.orderByChild(lectureGroupId).equalTo(lectureGroupId);
            groupIdQuery.addListenerForSingleValueEvent(new ValueEventListener() { //these listeners query data once so should only be single value events
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    tempList1.add((lectureGroup) dataSnapshot.getValue());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("Warning", "Lecture Group was not received");
                }
            });
        }

        if (lectureGroupName != null){
            Query groupNameQuery = this.list.orderByChild("groupName").equalTo(lectureGroupName);
            groupNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (teacherFirstName != null){
            Query teacherFirstNameQuery = this.list.orderByChild(teacherFirstName).equalTo(teacherFirstName);

        }

        if (teacherLastName != null){
            //do the same thing for all the inputs
        }

        if (School != null){
            //do the same thing for all the inputs
        }

        return tempList1;
    }

    /**
     * @Method deleteLectureGroup : deletes  a lecture group in the database by String id key
     * @param lectureGroup : Lecture Group Reference
     */
    public void deleteLectureGroup(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getGroupId()).removeValue();
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
    @Deprecated //This is a last resort method and should ideally not be called to avoid overhead
    public void updateLectureGroup(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getGroupId()).setValue(lectureGroup);
    }

    /**
     * @Method registerListeners : calls all listeners that are being applyed to this database reference
     * NOTE: Everytime you attach a listener to this Database reference append the method here
     * NOTE: Everytime you create an instance of this DAO and need to track data of a lecture group, invoke this method
     */
    public void registerDAOListeners(lectureGroup lectureGroup){
        onLectureGroupStatementsChanged(lectureGroup);
    }

    /**
     * Database Reference Listeners
     */
    @SuppressWarnings("ALL")
    private void onLectureGroupStatementsChanged(lectureGroup lectureGroup){
        this.list.child(lectureGroup.getGroupId()).child("statements").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                groupedStatementsCache = (LinkedList<groupedStatement>) dataSnapshot.getValue();
                Log.d("onChildAdded", dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                groupedStatementsCache = (LinkedList<groupedStatement>) dataSnapshot.getValue();
                Log.d("onChildChanged", dataSnapshot.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                groupedStatementsCache = (LinkedList<groupedStatement>) dataSnapshot.getValue();
                Log.d("onChildRemoved", dataSnapshot.toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                groupedStatementsCache = (LinkedList<groupedStatement>) dataSnapshot.getValue();
                Log.d("onChildRemoved", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "GROUPED STATEMENT EVENT LISTENER FAILED");
            }
        });
    }

    /**
     * Getters and Setters
     */
    public LinkedList<groupedStatement> getCachedStatements(){
        return this.groupedStatementsCache;
    }

}
