package app.classlink;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import app.classlink.helperClasses.activityParameters;
import app.classlink.helperClasses.viewHelperClass;
import app.classlink.parents.baseActivity;

/**
 * @Class field : Encapsulation of each field displayed on the sign up activity
 */
class field {

    public String name;
    public EditText inputBox;
    public ImageView imageBox;

    public field(String name, Context context){
        this.name = name;
        this.inputBox = new EditText(context);
        this.imageBox = new ImageView(context);
    }
}

/**
 * @Class signUp : Sign up class handler
 */
public class signUp extends baseActivity implements activityParameters {

    /** The functionality of this UI is such that if you want to include another field, just append  to this list*/
    ArrayList<String> keys = new ArrayList<>(Arrays.asList("First Name", "Last Name", "Email", "Username", "Password", "Confirm Password"));
    HashMap<String, field> fields = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        layoutSetup();
        submitButton();
    }
    /**
     * @Method  layoutSetup : Sets up all static UI components of the activity
     */
    @Override
    public void layoutSetup() {
        //Layout Setup
        this.activityLayout = (RelativeLayout) findViewById(R.id.activity_sign_up);
        this.activityLayout.setBackgroundResource(R.drawable.backgroundcolor);
        this.viewHelperClass = new viewHelperClass(this.activityLayout, getApplicationContext(), this.getWindowManager().getDefaultDisplay());

        //Text Setup
        this.viewHelperClass.addText("Sign-Up", "OpenSans-Regular", "BLACK", 18f, 15, 5);

        //Graphical Setup
        /** Field graphics*/
        for (int i = 0; i < keys.size(); i++){
            //Add each field to a hashmap
            fields.put(keys.get(i), new field(keys.get(i), this.viewHelperClass.getActivityContext()));
            //store fields in temp values just for code readability
            EditText tempEdit = fields.get(keys.get(i)).inputBox;
            ImageView tempImage = fields.get(keys.get(i)).imageBox;
            //add fields
            this.viewHelperClass.addText(keys.get(i), "OpenSans-Regular", "BLACK", 16f, 50,10*i + 20);
            this.viewHelperClass.addGraphicInputBox(tempEdit, R.drawable.inputbox, InputType.TYPE_CLASS_TEXT, 33, 10*i + 22, 0.5f, 0.5f);
        }
    }

    /**
     * @Method Submit button to store information in database
     */
    private void submitButton() {
    }


}
