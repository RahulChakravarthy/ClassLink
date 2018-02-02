package app.classlink.MVP.Model;

import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import rahul.uwaterloo.com.Data.DataObject;
import rahul.uwaterloo.com.androidpractice.Core;

public class DAO extends FirebaseDatabaseController {

    final private static long maxDefaultWaitTime = 5000; //Default wait 5 seconds for request to complete

    public DAO(String tableName) {
        super(tableName);
    }

    public synchronized Optional<List<DataObject>> getData(final List<String> keywordParams){
        DatabaseReference locationOfGetGeneral = this.databaseReference;
        for (String keywordParam : keywordParams){
            locationOfGetGeneral = locationOfGetGeneral.child(keywordParam);
        }
        final DatabaseReference locationOfGet =  locationOfGetGeneral;

        final List<DataObject> returner = new ArrayList<>();

        try {
        final CompletableFuture<List<DataObject>> ReturnerCompletableFuture =  CompletableFuture.supplyAsync(() -> {
            locationOfGet.addChildEventListener(new ChildEventListenerController());
            return returner;
        }, Core.scheduledApplicationThreadPool);
        return Optional.of(ReturnerCompletableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public synchronized void postData(final List<String> keywordParams, final List<DataObject> dataObjects){
        CompletableFuture.runAsync(() -> {
            DatabaseReference locationOfPost = this.databaseReference;
            for (String keywordParam : keywordParams){
                locationOfPost = locationOfPost.child(keywordParam);
            }

            for (DataObject dataObject : dataObjects){
                locationOfPost.child(dataObject.getStringId()).setValue(dataObject);
            }
        }, Core.scheduledApplicationThreadPool);
    }

    public synchronized void updateData(final List<String> keywordParams, final List<DataObject> dataObjects){
        this.postData(keywordParams,dataObjects);
    }

    public synchronized void deleteData(final List<String> keywordParams, final List<DataObject> dataObjects){
        CompletableFuture.runAsync(() -> {
            DatabaseReference locationOfDelete = this.databaseReference;
            for (String keywordParam : keywordParams){
                locationOfDelete = locationOfDelete.child(keywordParam);
            }

            for (DataObject dataObject : dataObjects){
                locationOfDelete.child(dataObject.getStringId()).removeValue();
            }
        }, Core.scheduledApplicationThreadPool);
    }

    public DAO getDAO(String DAOName) {
        Method method;
        try {
            method = this.getClass().getMethod(DAOName, DAO.class);
            return (DAO) method.invoke("get" + DAOName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            //Do nothing
            return null;
        }
    }
}
