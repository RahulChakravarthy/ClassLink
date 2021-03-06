package app.classlink.backend.statement.statementGrouping;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashMap;

import app.classlink.backend.core.DAO;
import app.classlink.backend.core.baseGroup;
import app.classlink.backend.misc.listNames;

/**
 * @Class groupedStatementDAO : DAO for accessing groupedStatements
 */
public class groupedStatementDAO extends DAO {

    protected HashMap<String, groupedStatement> groupedStatementCache;

    /**
     * @Constructor: initializes the connection
     */
    public groupedStatementDAO() {
        super(listNames.GROUPS);
        this.groupedStatementCache = new HashMap<>();
    }

    /**
     * @Method setCacheListener : set the cache listener on that specific part of the database and listen to changed values
     */
    public void setCacheListener(baseGroup group){
        this.list = this.list.child(group.getGroupType().toString()).child(group.getSchoolName().toString()).child(group.getGroupId()).child("groupedStatement");
        this.list.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                groupedStatementCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(groupedStatement.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                groupedStatementCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(groupedStatement.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                groupedStatementCache.remove(dataSnapshot.getValue(groupedStatement.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                groupedStatementCache.put(dataSnapshot.getKey(), dataSnapshot.getValue(groupedStatement.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ERROR", "CACHE ERROR");
            }
        });
    }

    /**
     * @Method addStatement : Appends a statement to the list of statements for the current group
     * @param newStatement : new statement object
     */
    public void addStatement(groupedStatement newStatement){
        this.list.child(newStatement.getStatementQuestion().getWrittenTime()).setValue(newStatement);
    }

    /**
     * @Method updateStatement : updates the current statement with a new statement value
     * @param updatedStatement : updated statement
     */
    public void updateStatement(groupedStatement updatedStatement){
        this.list.child(updatedStatement.getStatementQuestion().getWrittenTime()).setValue(updatedStatement);
    }

    /**
     * @Method deleteStatement : deletes
     * @param nullStatement
     */
    public void deleteStatement(groupedStatement nullStatement){
        this.list.child(nullStatement.getStatementQuestion().getWrittenTime()).removeValue();
    }

    public HashMap<String, groupedStatement> getGroupedStatementCache(){
        return groupedStatementCache;
    }
}
