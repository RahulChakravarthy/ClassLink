package app.classlink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.backend.core.baseActivity;

public class studyMenu extends baseActivity implements activityParameters {

    private ImageView create;
    private ImageView join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);

        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_study_menu);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        layoutSetup();
        buttonSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        this.activityLayout.setBackgroundResource(R.drawable.bg);

        create = (ImageView) findViewById(R.id.buttonCreateStudyGroup);
        join = (ImageView) findViewById(R.id.buttonJoinStudyGroup);

        viewHelperClass.imageToButton(create);
        viewHelperClass.imageToButton(join);


    }

    public void buttonSetup() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studyMenu.this, studyCreate.class));
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studyMenu.this, studyJoin.class));
            }
        });
    }
}
