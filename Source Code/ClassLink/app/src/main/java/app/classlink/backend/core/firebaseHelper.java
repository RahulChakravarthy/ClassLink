package app.classlink.backend.core;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Future;

/**
 * @Class firebaseHelper : adapter class that enables our app data to interact with firebase cloud servers
 */
public class firebaseHelper {

    protected DatabaseReference list;
    protected FirebaseDatabase db;

    /**
     * @Constructor: initializes the connection
     */
    public firebaseHelper(String listName) {
        this.db = FirebaseDatabase.getInstance();
        this.list = this.db.getReference(listName);
    }
}
