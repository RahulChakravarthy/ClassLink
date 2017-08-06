package app.classlink.backend.groups.study;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.Hashtable;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.student.student;
import app.classlink.backend.users.teacher.teacher;


public class StudyGroupDAO extends DAO {

    protected Hashtable<String, studyGroup> cache; //key = id of group, value = studyGroup object

    public StudyGroupDAO() {
        super(listNames.GROUPS);
        this.list = this.list.child(listNames.STUDYGROUPS);
        this.cache = new Hashtable<>();
    }

      public boolean createStudyGroup(String studyGroupName, String studyGroupDescription, student groupCreator, School schoolName){
          if (getStudyGroupByName(studyGroupName) == null){
              String studyGroupId = this.list.child(schoolName.toString()).push().getKey();
              studyGroup newGroup = new studyGroup(studyGroupId, studyGroupName, studyGroupDescription, groupCreator);
              this.list.child(schoolName.toString()).child(studyGroupId).setValue(newGroup);
              return true;
          } else {
              return false;
          }
    }

    public boolean createStudyGroup(studyGroup studyGroup){
        String studyGroupId = this.list.child(studyGroup.getSchoolName().toString()).push().getKey();
        this.list.child(studyGroup.getSchoolName().toString()).child(studyGroupId).setValue(studyGroup);
        return true;
    }

    public studyGroup getStudyGroupById(String studyGroupId) {
        return cache.get(studyGroupId);
    }

    public studyGroup getStudyGroupByName(String name){
        ArrayList<studyGroup> temp = new ArrayList<>();
        for (studyGroup child : cache.values()){
            if (child.getGroupName().equals(name)){
                temp.add(child);
            }
        }
        return temp.size() != 0? temp.get(0): null;
    }

    public ArrayList<studyGroup> getStudyGroupBySubName(String subName){
        return  null;
    }

    public ArrayList<studyGroup> getStudyGroupByCreatorName(String creatorName){
        ArrayList<studyGroup> temp = new ArrayList<>();
        for (studyGroup child : cache.values()){
            if (creatorName != null){
                if (child.getGroupCreator().getFirstName().equals(creatorName)){
                    temp.add(child);
                }
            } else if (creatorName != null){
                if (child.getGroupCreator().getFirstName().equals(creatorName)){
                    temp.add(child);
                }
            } else {

            }
        }
        return temp;
    }

    public ArrayList<studyGroup> getAllStudyGroups(){
        return new ArrayList<>(cache.values());
    }

    public void deleteStudyGroup(studyGroup studyGroup){
        this.list.child(studyGroup.getSchoolName().toString()).child(studyGroup.getGroupId()).removeValue();
    }

    private void deleteAllLectureGroups(){
        this.list.removeValue();
    }

    @Deprecated
    public void updateStudyGroup(studyGroup studyGroup){
        this.list.child(studyGroup.getSchoolName().toString()).child(studyGroup.getGroupId()).setValue(studyGroup);
    }

    public void setCacheListener(String schoolName){
        this.list.child(schoolName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(studyGroup.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(studyGroup.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cache.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(studyGroup.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getDetails());
            }
        });
    }

}
