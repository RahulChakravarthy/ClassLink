package app.classlink.backend.core;


import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

import app.classlink.backend.core.firebaseHelper;
import app.classlink.backend.misc.School;
import app.classlink.backend.users.user.user;
import app.classlink.backend.users.user.userDAO;
import app.classlink.helperClasses.*;


abstract public class baseActivity extends AppCompatActivity {

    protected RelativeLayout activityLayout; //Activity Layout
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity
    protected user sessionUser; //user who is logged in

    protected FirebaseAuth userAuth; //Current user session

    /**
     * @Method retrieveUser : passes in the userAuth information gathered from firebase and queries the user database for said user
     * @return true if session sessionUser is instantiated or false if not (redirect to login page if false)
     */
    protected boolean retrieveUser(){
        if (userAuth.getCurrentUser() != null){
            userDAO userDAO = new userDAO();
            this.sessionUser = userDAO.getUsers(null,null,null,userAuth.getCurrentUser().getEmail(), School.UNIVERSITY_OF_WATERLOO).get(0);
            return true;
        }
        return false;
    }

}
