package app.classlink.frontend;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

import app.classlink.R;
import app.classlink.backend.core.baseActivity;
import app.classlink.helperClasses.activityParameters;

public class studyRoom extends baseActivity implements activityParameters {

    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_room);

        l = (LinearLayout) findViewById(R.id.activity_study_room);

        //firebaseHelper.startList();

        layoutSetup();
    }

    /**
     *@Method setActivityDAOListeners : Set all listeners you wish to use in this activity so that they start caching data
     */
    protected void setActivityDAOListeners() {

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

        final Random rand = new Random();

//        ask.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                question q = new question(field.getText().toString(), rand.nextInt());
//                firebaseHelper.addItem(q);
//                field.setText(null);
//
//            }
//        });

        Button get = new Button(getApplicationContext());
        get.setText("Get!");
        l.addView(get);

//        get.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<Integer, question> questions = firebaseHelper.getListContents();
//
//                for (question q : questions.values()) {
//                    TextView t = new TextView(getApplicationContext());
//                    t.setText(q.getQuestionText());
//                    t.setTextColor(Color.BLACK);
//                    l.addView(t);
//                    Log.d("Displaying...", "Displayed question " + q.getQuestionText());
//                }
//            }
//        });
    }
}
