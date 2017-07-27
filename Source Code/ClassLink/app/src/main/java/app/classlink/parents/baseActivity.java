package app.classlink.parents;


import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

import app.classlink.backend.core.firebaseHelper;
import app.classlink.helperClasses.*;


abstract public class baseActivity extends AppCompatActivity {

    protected RelativeLayout activityLayout; //Activity Layout
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity
    protected FirebaseAuth userAuth; //Current user session

}
