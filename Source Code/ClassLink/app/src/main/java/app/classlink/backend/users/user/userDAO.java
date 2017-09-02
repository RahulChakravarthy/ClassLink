package app.classlink.backend.users.user;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Hashtable;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.listNames;
import app.classlink.backend.users.administrator.administrator;
import app.classlink.backend.users.administrator.administratorDAO;
import app.classlink.backend.users.student.student;
import app.classlink.backend.users.student.studentDAO;
import app.classlink.backend.users.teacher.teacher;
import app.classlink.backend.users.teacher.teacherDAO;

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

    /**
     * @Method createUser : creates a user and appends it to its appropriate position in the database
     */
    public void createUser(user newUser){
        switch (newUser.getClass().getSimpleName()){
            case "student" :
                studentDAO studentDAO = new studentDAO();
                studentDAO.createStudent((student) newUser);
                break;
            case "teacher" :
                teacherDAO teacherDAO = new teacherDAO();
                teacherDAO.createTeacher((teacher) newUser);
                break;
            case "administrator" :
                administratorDAO administratorDAO = new administratorDAO();
                administratorDAO.createAdmin((administrator) newUser);
                break;
            default:
                Log.d("ERROR", "USER CATEGORY DOES NOT EXIST");
                break;
        }
    }

    /**
     * @Method deleteUserByEmail : removes a user from the database
     */
    public void deleteUserByEmail(user currentUser, FirebaseAuth userAuth){
        //delete user in our database
        switch (currentUser.getClass().getSimpleName()){
            case "administrator" :
                administratorDAO administratorDAO = new administratorDAO();
                administratorDAO.deleteAdminById(currentUser.getUserId());
                break;
            case "teacher" :
                teacherDAO teacherDAO = new teacherDAO();
                teacherDAO.deleteTeacherById(currentUser.getUserId());
                break;
            case "student" :
                studentDAO studentDAO = new studentDAO();
                studentDAO.deleteStudentById(currentUser.getUserId());
                break;
            default:
                Log.d("Error", "Invalid User");
                break;
        }
        //re-authenticate user and delete their account FIX THIS LATER

        userAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d("USER: ", "DELETED");
                } else {
                    Log.d("USER: ", "NOT DELETED");
                }
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
        return temp.size() == 0? null : temp.get(0);
    }

    /**
     * @Method setCacheListener : sets the user cache listener to listen to user data in the database
     */
    public void setCacheListener() {
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
}
