package app.classlink.backend.core;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.classlink.backend.statement.statementType.question;

/**
 *
 * Created by jaywe on 2017-07-15.
 *
 */

public class firebaseHelper {

    protected DatabaseReference list;
    protected FirebaseDatabase db;

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

        this.list.addChildEventListener((new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    question q = new question(dataSnapshot.getValue(question.class));
                    Log.d("Good", "Question is: " + q.getField());
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
}
