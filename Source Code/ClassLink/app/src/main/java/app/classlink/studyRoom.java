package app.classlink;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.classlink.backend.utility.Question;
import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class studyRoom extends baseActivity implements activityParameters {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_room);

        final RelativeLayout l = (RelativeLayout) findViewById(R.id.activity_study_room);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();

        final EditText field = new EditText(getApplicationContext());
        l.addView(field);
        field.setTextColor(Color.BLACK);

        Button ask = new Button(getApplicationContext());
        ask.setText("Ask!");
        l.addView(ask);

        final DatabaseReference questionList = db.getReference("Questions: " );
        questionList.addChildEventListener((new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    Question q1 = new Question(dataSnapshot.getValue(Question.class));
                    Log.d("Good", "Question is: " + q1.getField());

                    TextView tv1 = new TextView(getApplicationContext());
                    tv1.setText(q1.getField());
                    tv1.setTextColor(Color.BLACK);
                    l.addView(tv1);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }));

        ask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Question q = new Question(0, field.getText().toString());
                questionList.push().setValue(q, 0);
                field.setText(null);
            }
        });

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {

    }
}
