package app.classlink;

import android.os.Bundle;

import app.classlink.backend.core.baseActivity;

/**
 * @Class spashScreen : displays the class link logo while main activity loads
 */
public class splashScreen extends baseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void setActivityDAOListeners() {
        //No DAOs being accessed
    }
}
