package app.classlink.helperClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
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
     * @Method addText : Programmatic way of quickly adding custom text to the activity (try to add font parameter)
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
     * @param xPosition : set Object x-position
     * @param yPosition : set Object y-position
     * @param xScale : set the Objects x-scale
     * @param yScale : set Object y-scale
     * (Add an optional argument to pass on click listener functions via lambdas and instantiate them)
     */
    public void addGraphics(View objectView, float xPosition, float yPosition, float xScale, float yScale){
        objectView.setScaleX((xScale <= 0)? 1: xScale);
        objectView.setScaleY((yScale <= 0)? 1: yScale);
        objectView.setX(xPosition);
        objectView.setY(yPosition);
        this.activityLayout.addView(objectView);
    }

    /**
     * @Method addButtonImage : Take an ImageView, give it a resource to display, turn it into a button along with up/down animation, and add it to the Activity screen
     * @param imageView : resource for the button to display
     * @param resourceId : id for the resource, in res folder
     * @param xPosition : object x-position
     * @param yPosition : object y-position
     * @param xScale : object x-scale
     * @param yScale : object y-scale
     * @param button : true turns the image into a button, false means no onTouchListener
     */
    public void addButtonImage(final ImageView imageView, int resourceId, float xPosition, float yPosition, float xScale, float yScale, boolean button) {
        imageView.setImageResource(resourceId);
        imageView.setScaleX((xScale <= 0) ? 1 : xScale);
        imageView.setScaleY((yScale <= 0) ? 1 : yScale);
        imageView.setX(xPosition);
        imageView.setY(yPosition);

        if(button) {

            //Setting up the animation: on action down, grey out the image
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            imageView.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            imageView.invalidate();

                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            imageView.getDrawable().clearColorFilter();
                            imageView.invalidate();
                            break;
                    }
                    return false;
                }
            });
        }

        this.activityLayout.addView(imageView);
    }
}
