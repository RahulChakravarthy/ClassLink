package app.classlink.backend.groups.lecture;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
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
        if (this.getLectureGroupByName(lectureGroupName, schoolName).size() == 0){
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
        if (this.getLectureGroupByName(lectureGroup.getGroupName(), lectureGroup.getSchoolName()).size() == 0){
            String lectureGroupId = this.list.push().getKey();
            this.list.child(lectureGroupId).setValue(lectureGroup);
            return true; //lecture group is not a duplicate
        }
        return false;
    }

    /**
     * @Method getLectureGroupById : returns a lecture group by id
     * @param lectureGroupId : input lecture ID
     * @return lectureGroup Array List
     */
    public ArrayList<lectureGroup> getLectureGroupById(final String lectureGroupId){
        final ArrayList<lectureGroup> tempList = new ArrayList<>();
        Query groupIdQuery = this.list;
        groupIdQuery.addListenerForSingleValueEvent(new ValueEventListener() { //these listeners query data once so should only be single value events
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot group : dataSnapshot.getChildren()){
                    if (group.getKey().equals(lectureGroupId)){
                        tempList.add(group.getValue(lectureGroup.class));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Warning", "Lecture Group was not received");
            }
        });
        return tempList;
    }

    /**
     * @Method getLectureGroupByName : returns the lecture group with that name (only one since there shouldnt be a lecture group with the exact same name)
     * @param name : lecture group name
     * @param school : school in which you are searching through
     * @return lectureGroup Array List
     */
    public ArrayList<lectureGroup> getLectureGroupByName(final String name, School school){
        final ArrayList<lectureGroup> tempList = new ArrayList<>();
        Query groupNameQuery = this.list;
        groupNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot group : dataSnapshot.getChildren()){
                    if (group.getValue(lectureGroup.class).getGroupName().equals(name)){
                        tempList.add(group.getValue(lectureGroup.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Warning", "Lecture Group was not received");
            }
        });
        return filterBySchool(tempList, school);
    }

    /**
     * @Method getLectureGroupByTeacher : returns all lecture groups hosted by a provided teacher
     * @param teacherFirstName : teacher first name whom you wish to view all lecture groups
     * @return Arraylist lectureGroups
     */
    public ArrayList<lectureGroup> getLectureByTeacherFirstName(final String teacherFirstName, School school){
        final ArrayList<lectureGroup> tempList = new ArrayList<>();
            Query teacherFirstNameQuery = this.list;
            teacherFirstNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot group : dataSnapshot.getChildren()){
                        if (group.getValue(lectureGroup.class).getLectureCreator().getFirstName().equals(teacherFirstName)){
                            tempList.add(group.getValue(lectureGroup.class));
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("Warning", "Lecture Group was not received");
                }
            });
        return filterBySchool(tempList, school);
    }

    /**
     * @Method getLectureGroupByTeacher : returns all lecture groups hosted by a provided teacher
     * @param teacherLastName : teacher last name whom you wish to view all lecture groups
     * @return Arraylist lectureGroups
     */
    public ArrayList<lectureGroup> getLectureByTeacherLastName(final String teacherLastName, School school){
        final ArrayList<lectureGroup> tempList = new ArrayList<>();
        Query teacherLastNameQuery = this.list;
        teacherLastNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot group : dataSnapshot.getChildren()){
                    if (group.getValue(lectureGroup.class).getLectureCreator().getFirstName().equals(teacherLastName)){
                        tempList.add(group.getValue(lectureGroup.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Warning", "Lecture Group was not received");
            }
        });
        return filterBySchool(tempList, school);
    }

    /**
     * @Method getAllLectureGroups : retrieves all lecture groups for a specific school
     */
    public ArrayList<lectureGroup> getAllLectureGroups(School school){
        final ArrayList<lectureGroup> tempList = new ArrayList<>();
        Query schoolQuery = this.list;
        schoolQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot group : dataSnapshot.getChildren()){
                    tempList.add(group.getValue(lectureGroup.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Warning", "Lecture Group was not received");
            }
        });
        return filterBySchool(tempList, school);
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

    /**
     * @Method filterBySchool : filters an array of lecture groups and deletes all that arent of the correct school
     */
    public ArrayList<lectureGroup> filterBySchool(ArrayList<lectureGroup> tempList, School school){
        for (int i = 0; i < tempList.size(); i++){
            if (tempList.get(i).getSchoolName() != school){
                tempList.remove(i);
            }
        }
        return tempList;
    }
}
