package jaywe.project;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout rl = (LinearLayout) findViewById(R.id.activity_main);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        //final DatabaseReference ref = db.getReference("test");


        final int questions = 0;

        /*Button upvote = new Button(getApplicationContext());
        Button downvote = new Button(getApplicationContext());
        Button reset = new Button(getApplicationContext());

        upvote.setText("Upvote");
        downvote.setText("Downvote");
        reset.setText("Reset");

        rl.addView(upvote);
        rl.addView(downvote);
        rl.addView(reset);

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.vote(1);
                ref.setValue(q);
            }
        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.vote(0);
                ref.setValue(q);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q.resetVotes();
                ref.setValue(q);
            }
        });*/

        //Firebase questionList = ref.child("Questions");

        final EditText field = new EditText(getApplicationContext());
        rl.addView(field);
        field.setTextColor(Color.BLACK);

        Button ask = new Button(getApplicationContext());
        ask.setText("Ask!");
        rl.addView(ask);

        /*final EditText field2 = new EditText(getApplicationContext());
        rl.addView(field2);
        field2.setTextColor(Color.BLACK);

        Button answer = new Button(getApplicationContext());
        answer.setText("Answer!");
        rl.addView(answer);*/

        final DatabaseReference questionList = db.getReference("Questions: ");
        final DatabaseReference answerList = db.getReference("Answers: " );

        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });*/

        questionList.addChildEventListener((new ChildEventListener() {
            //@Override
            /*public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Question q1 = new Question(dataSnapshot.getValue(Question.class));
                    Log.d("Good", "Question is: " + q1.getField());
                }
                *//*TextView tv1 = new TextView(getApplicationContext());
                tv1.setText(q1.getField());
                tv1.setTextColor(Color.BLACK);
                rl.addView(tv1);*//*
            }*/

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    Question q1 = new Question(dataSnapshot.getValue(Question.class));
                    Log.d("Good", "Question is: " + q1.getField());

                    TextView tv1 = new TextView(getApplicationContext());
                    tv1.setText(q1.getField());
                    tv1.setTextColor(Color.BLACK);
                    rl.addView(tv1);
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
                Log.w("FML", "FML");
            }
        }));

        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = new Question(questions, field.getText().toString());
                questionList.push().setValue(q, questions);

                field.setText(null);
            }
        });

        /*answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer a = new Answer(questions, field2.getText().toString());

                answerList.push().setValue(a, questions);
                field2.setText(null);
            }
        });
*/
    }
}
