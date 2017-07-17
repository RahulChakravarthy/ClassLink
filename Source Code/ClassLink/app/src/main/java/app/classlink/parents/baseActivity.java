package app.classlink.parents;


import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import app.classlink.backend.core.firebaseHelper;
import app.classlink.helperClasses.*;


public class baseActivity extends AppCompatActivity {

    protected RelativeLayout activityLayout; //Activity Layout
    protected viewHelperClass viewHelperClass; //Helper class to output views to the activity
    public final firebaseHelper f = new firebaseHelper();

}
