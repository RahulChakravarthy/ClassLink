package app.classlink.backend.core;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.*;


abstract public class baseActivity extends AppCompatActivity {

    protected RelativeLayout activityLayout; //Activity Layout
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity


    protected FirebaseAuth userAuth; //Current user session

    /**
     * @Method retrieveUser : passes in the userAuth information gathered from firebase and queries the user database for said user
     * @return true if session sessionUser is instantiated or false if not (redirect to login page if false)
     */
    protected boolean retrieveUser(){
        try {
            return userAuth.getCurrentUser() != null;
        } catch (NullPointerException e){
            return false;
        }
    }

    /**
     * @Method intenetConnection : this method determines if the phone is connected to a network, call this everytime you need to access the database to ensure the user is connected
     * @return boolean : true if there is a connection, false otherwise
     */
    protected boolean internetConnection(){
        ConnectivityManager connection = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connection.getActiveNetworkInfo();

        if (networkInfo != null){
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Network unavailable, please try again later", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
