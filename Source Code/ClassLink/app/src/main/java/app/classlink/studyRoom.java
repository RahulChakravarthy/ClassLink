package app.classlink;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import app.classlink.helperClasses.activityParameters;
import app.classlink.parents.baseActivity;

public class studyRoom extends baseActivity implements activityParameters {

    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_room);
        //this.activityLayout = (RelativeLayout) findViewById(R.id.activity_study_room);

        l = (LinearLayout) findViewById(R.id.activity_study_room);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();

        final EditText field = new EditText(getApplicationContext());
        l.addView(field);
        field.setTextColor(Color.BLACK);
        field.setText("text...");

        Button ask = new Button(getApplicationContext());
        ask.setText("Ask!");
        l.addView(ask);

        final DatabaseReference questionList = db.getReference("Questions: " );
        questionList.addChildEventListener((new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
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
            }
        });

        layoutSetup();
    }

    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        l.setBackgroundColor(Color.WHITE);
    }
}
