package app.classlink.backend.core;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
     * @Method addItem: add any object/class to the current list
     * @param o: whatever you want to add
     */
    public void addItem(Object o) {
        this.list.push().setValue(o);
    }

}
