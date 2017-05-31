package app.classlink;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;

public class mainAppMenu extends AppCompatActivity implements activityParameters {
    protected RelativeLayout mainAppMenuLayout;
    protected viewHelperClass viewHelperClass;
    private ImageView studyGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_menu);

        mainAppMenuLayout = (RelativeLayout)findViewById(R.id.activity_main_app_menu);
        viewHelperClass = new viewHelperClass(mainAppMenuLayout, getApplicationContext());

        layoutSetup();
        buttonSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.mainAppMenuLayout.setBackgroundColor(Color.parseColor("#3d80b0"));
        studyGroup = new ImageView(getApplicationContext());
        studyGroup.setImageResource(R.drawable.button_study_group_up);
        viewHelperClass.addGraphics(studyGroup, -300, 350, 0.40f, 0.40f);
    }

    public void buttonSetup() {
        studyGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        studyGroup.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        studyGroup.invalidate();

                        startActivity(new Intent(mainAppMenu.this, app.classlink.studyGroupMenu.class));
                        break;
                }
                return false;
            }
        });
    }
}
