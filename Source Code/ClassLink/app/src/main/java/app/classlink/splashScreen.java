package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import app.classlink.backend.core.baseActivity;

/**
 * @Class spashScreen : displays the class link logo while main activity loads
 */
public class splashScreen extends baseActivity {

    private final int SPLASH_WAIT_TIME = 3000;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startSplashScreen();
    }

    /**
     * @Method startSplashScreen : starts and displays splash screen on app load up
     */
    private void startSplashScreen() {
        this.loading = (ProgressBar) findViewById(R.id.loading_progress);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.setProgress(100);
                Intent loginScreen = new Intent(splashScreen.this, login.class);
                startActivity(loginScreen);
                splashScreen.this.finish();
            }
        }, SPLASH_WAIT_TIME);
    }
}
