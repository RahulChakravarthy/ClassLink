package ca.uwaterloo.ca.classlink.MiscHelperClasses;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Class viewHelperClass : This class has implemented static methods to aid with output views to various activities
 */

public class viewHelperClass {

    private RelativeLayout activityLayout; //Captured activity layout
    private Context activityContext; //Captured activity context

    /**
     * @Consructor
     * @param activityLayout: captures Relative layout of Activity
     * @param activityContext: captures Context of activity
     */
    public viewHelperClass(RelativeLayout activityLayout, Context activityContext){
        this.activityLayout = activityLayout;
        this.activityContext = activityContext;
    }

    /**
     * @Method addText : Programmatic way of quickly adding custom text to the activity
     * @param message : Message to be outputted to activity
     * @param color : Message color
     * @param size: Message size
     * @param xPosition : Message x position
     * @param yPosition : Message y position
     */
    public void addText(String message, String color, float size, float xPosition, float yPosition){
        TextView tempText = new TextView(this.activityContext);
        tempText.setText(message);
        tempText.setTextSize((size == 0)? 12: size);
        tempText.setTextColor((color == null)? Color.BLACK: Color.parseColor(color));
        tempText.setX(xPosition);
        tempText.setY(yPosition);
        this.activityLayout.addView(tempText);
    }

    /**
     * @Method addGraphics : Programmatic way of adding visuals such as vector graphics to the Activity Screen
     * @param objectView : object that you want added to screen
     */
    public void addGraphics(View objectView, float xPosition, float yPosition){
        objectView.setX(xPosition);
        objectView.setY(yPosition);
        this.activityLayout.addView(objectView);

    }
}
