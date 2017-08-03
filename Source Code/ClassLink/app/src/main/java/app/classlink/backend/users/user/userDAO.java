package app.classlink.backend.users.user;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.administrator.administrator;
import app.classlink.backend.users.student.student;
import app.classlink.backend.users.teacher.teacher;

/**
 * @Class userDAO : DAO for user class individuals
 */
public class userDAO extends DAO {

    protected Hashtable<String, user> cache; //cache holds every user in the program, refactor
    protected Hashtable<String, administrator> adminCache; //holds admins
    protected Hashtable<String, student> studentCache; //holds students
    protected Hashtable<String, teacher> teacherCache; //holds teachers

    /**
     * @Constructor: initializes the connection
     */
    public userDAO() {
        super(listNames.USERS);
        cache = new Hashtable<>();
        adminCache = new Hashtable<>();
        studentCache = new Hashtable<>();
        teacherCache = new Hashtable<>();
    }

    @Override
    public void setCacheListener(String schoolName) {
        //add admin users
        this.list.child(listNames.ADMIN).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
                adminCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
                adminCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cache.remove(dataSnapshot.getKey());
                adminCache.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
                adminCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(administrator.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getDetails());
            }
        });

        //add teacher users
        this.list.child(listNames.TEACHERS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));
                teacherCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));
                teacherCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cache.remove(dataSnapshot.getKey());
                teacherCache.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));
                teacherCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(teacher.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getDetails());
            }
        });
        //add student users
        this.list.child(listNames.STUDENTS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
                studentCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
                studentCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                cache.remove(dataSnapshot.getKey());
                studentCache.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                cache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
                studentCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(student.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getDetails());
            }
        });
    }

    /**
     * @Method getUserByEmail : used at the start of an activity to get the instance of user signed in
     */
    public user getUserByEmail(String email){
        ArrayList<user> temp = new ArrayList<>();
        for (user child : cache.values()){
            if (child.getEmail().equals(email)){
                temp.add(child);
            }
        }
        return temp.get(0);
    }
}
