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
public class firebaseHelper implements listNames{

    protected DatabaseReference list;
    protected FirebaseDatabase db;

    /**
     * @Constructor: initializes the connection
     */
    public firebaseHelper(String listName) {
        this.db = FirebaseDatabase.getInstance();
        this.list = this.db.getReference(listName);
    }

    /**
     * @Method startList: start the reference to the specified list/table/entry in firebase
     * @return: DatabaseReference of the list you're looking at
     */
    public DatabaseReference startList() {
        this.list.addChildEventListener((new ChildEventListener() {
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

}
