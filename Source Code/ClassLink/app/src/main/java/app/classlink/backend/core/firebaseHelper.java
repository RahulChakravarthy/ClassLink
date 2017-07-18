package app.classlink.backend.core;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import app.classlink.backend.statement.statementType.question;

/**
 * @Class firebaseHelper : adapter class that enables our app data to interact with firebase cloud servers
 */
public class firebaseHelper {

    protected DatabaseReference list;
    protected FirebaseDatabase db;
    protected HashMap<Integer, question> listContents;

    /**
     * @Constructor: initializes the connection
     */
    public firebaseHelper() {
        this.db = FirebaseDatabase.getInstance();
    }

    /**
     * @Method startList: start the reference to the specified list/table/entry in firebase
     * @param listName: the name of the entry
     * @return: DatabaseReference of the list you're looking at
     */
    public DatabaseReference startList(String listName) {
        this.list = this.db.getReference(listName);
        this.listContents = new HashMap<>();

        this.list.addChildEventListener((new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    question q = new question(dataSnapshot.getValue(question.class));
                    Log.d("Addition: ", "Question is: " + q.getQuestionText());
                    listContents.put(q.getUserId(), q);
                }
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
                Log.w("Database Error", "Failed to execute value", databaseError.toException());
            }
        }));

        return list;
    }

    /**
     * @Method addItem: add any object/class to the current list
     * @param o: whatever you want to add
     */
    public void addItem(Object o) {
        this.list.push().setValue(o);
    }

    public HashMap<Integer, question> getListContents() { return this.listContents; }
}
