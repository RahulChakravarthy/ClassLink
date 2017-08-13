package app.classlink.backend.statement.statementGrouping;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.LinkedList;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.core.listNames;

public class groupedStatementDAO extends DAO{

    protected LinkedList<groupedStatement> groupedStatementCache;

    /**
     * @Constructor: initializes the connection
     */
    public groupedStatementDAO() {
        super(listNames.GROUPS);
    }

    /**
     * @Method setCacheListener : set the cache listener on that specific part of the database and listen to changed values
     */
    public void setCacheListener(baseGroup group){
        this.list = this.list.child(group.getGroupType().toString()).child(group.getSchoolName().toString()).child(group.getGroupId()).child("statements");
        this.list.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                groupedStatementCache.add(dataSnapshot.getValue(groupedStatement.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //TBD
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //TBD
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //TBD
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", "CACHE ERROR");
            }
        });
    }
}
