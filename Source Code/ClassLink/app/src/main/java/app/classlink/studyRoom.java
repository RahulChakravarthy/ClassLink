package app.classlink;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import app.classlink.backend.statement.statementType.question;

import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class studyRoom extends baseActivity implements activityParameters {

    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_room);

        l = (LinearLayout) findViewById(R.id.activity_study_room);

        f.startList("Questions: ");

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        l.setBackgroundColor(Color.WHITE);
        final EditText field = new EditText(getApplicationContext());
        l.addView(field);
        field.setTextColor(Color.BLACK);
        field.setText("text...");

        Button ask = new Button(getApplicationContext());
        ask.setText("Ask!");
        l.addView(ask);

        ask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                question q = new question(field.getText().toString(), 0);
                f.addItem(q);
                field.setText(null);

            }
        });
    }
}
