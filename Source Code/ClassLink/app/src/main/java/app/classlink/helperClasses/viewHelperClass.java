package app.classlink.helperClasses;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * @Class viewHelperClass : This class has implemented methods to aid with output views to various activities
 */

public class viewHelperClass {

    private RelativeLayout activityLayout; //Captured activity layout
    private Context activityContext; //Captured activity context

    //Capture phone's resolution and base and graphics/textview positioning on this
    private int phoneHeight;
    private int phoneWidth;

    /**
     * @Consructor
     * @param activityLayout : captures Relative layout of Activity
     * @param activityContext : captures Context of activity
     * @param display : display settings of the phone
     */
    public viewHelperClass(RelativeLayout activityLayout, Context activityContext, Display display){
        this.activityLayout = activityLayout;
        this.activityContext = activityContext;

        //get Device information
        Point size = new Point();
        display.getSize(size);
        this.phoneWidth = size.x;
        this.phoneHeight = size.y;
    }

    /**
     * @Method addText : Programmatic way of quickly adding custom text to the activity (try to add font parameter)
     * @param message : Message to be outputted to activity
     * @param fontType : font of the text from assets/fontstyles
     * @param color : Message color
     * @param positionType : 1 = anchor by left edge, 2 = anchor by center , 3 = anchor by right edge
     * @param size : Message size
     * @param xPosition : Message x position in %
     * @param yPosition : Message y position in %
     */
    public void addText(String message, String fontType, String color, final int positionType, float size, float xPosition, float yPosition){
        final TextView tempText = new TextView(this.activityContext);
        tempText.setText(message);

        Typeface font = Typeface.createFromAsset(this.activityContext.getAssets(), "fonts/" + fontType + ".ttf");
        tempText.setTypeface(font);

        tempText.setTextColor((color == null)? Color.BLACK: Color.parseColor(color));

        tempText.setTextSize((size == 0)? 12: size);

        tempText.setX(xPosition * this.getPhoneWidth() * 0.01f);
        tempText.setY(yPosition * this.getPhoneHeight() * 0.01f);
        tempText.postDelayed(new Runnable() {
            @Override
            public void run() {
                tempText.invalidate();

                switch (positionType){
                    case 2:
                        tempText.setX(tempText.getX() - (tempText.getWidth()/2));
                        tempText.setY(tempText.getY() - (tempText.getHeight()/2));
                        break;
                    case 3:
                        tempText.setX(tempText.getX() - (tempText.getWidth()));
                        tempText.setY(tempText.getY() - (tempText.getHeight()/2));
                        break;
                }
            }
        }, 0);
        this.activityLayout.addView(tempText);
    }

    /**
     * @Method addText : addText with reference to TextView
     * @param tempText : Reference to textview
     * @param fontType : font of the text from assets/fontstyles
     * @param color : Message color
     * @param size : Message size
     * @param xPosition : Message x position in %
     * @param yPosition : Message y position in %
     */
    public void addText(final TextView tempText, String message, String fontType, final String color, float size, float xPosition, float yPosition, boolean button){
        tempText.setText(message);

        Typeface font = Typeface.createFromAsset(this.activityContext.getAssets(), "fonts/" + fontType + ".ttf");
        tempText.setTypeface(font);

        tempText.setTextColor((color == null)? Color.BLACK: Color.parseColor(color));

        tempText.setTextSize((size == 0)? 12: size);

        tempText.setX(xPosition * this.getPhoneWidth() * 0.01f);
        tempText.setY(yPosition * this.getPhoneHeight() * 0.01f);
        tempText.postDelayed(new Runnable() {
            @Override
            public void run() {
                tempText.invalidate();
                tempText.setX(tempText.getX() - (tempText.getWidth()/2));
                tempText.setY(tempText.getY() - (tempText.getHeight()/2));
            }
        }, 0);

        if(button) {
            //Setting up the animation: on action down, grey out the image
            tempText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            tempText.setTextColor(Color.BLUE);
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            tempText.setTextColor((color == null)? Color.BLACK: Color.parseColor(color));
                            break;
                    }
                    return false;
                }
            });
        }

        this.activityLayout.addView(tempText);
    }

    /**
     * @Method editGraphics : Programmatic way of editing visuals such as vector graphics to the Activity Screen
     * @param objectView : object that you want added to screen
     * @param xPosition : set Object x-position
     * @param yPosition : set Object y-position
     * @param xScale : set the Objects x-scale
     * @param yScale : set Object y-scale
     * (Add an optional argument to pass on click listener functions via lambdas and instantiate them)
     */
    private void editGraphics(final View objectView, float xPosition, float yPosition, float xScale, float yScale){
        objectView.setScaleX((xScale <= 0)? 1: xScale);
        objectView.setScaleY((yScale <= 0)? 1: yScale);
        objectView.setX(xPosition * this.getPhoneWidth() * 0.01f);
        objectView.setY(yPosition * this.getPhoneHeight() * 0.01f);

        objectView.postDelayed(new Runnable() {
            @Override
            public void run() {
                objectView.invalidate();
                objectView.setX(objectView.getX() - (objectView.getWidth()/2));
                objectView.setY(objectView.getY() - (objectView.getHeight()/2));
            }
        },0);
    }

    /**
     * @Method imageToButton : Take an existing ImageView and turn it into a button with an onTouchListener animation
     * @param imageView : ImageView to turn into button
     */
    public void imageToButton(final ImageView imageView) {
        imageView.setAlpha(0.75f);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imageView.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        imageView.setAlpha(1.0f);
                        imageView.invalidate();

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        imageView.getDrawable().clearColorFilter();
                        imageView.setAlpha(0.75f);
                        imageView.invalidate();
                        break;
                }
                return false;
            }
        });
    }

    /**
     * @Method addGraphics : Take an ImageView, give it a resource to display, turn it into a button along with up/down animation, and add it to the Activity screen
     * @param imageView : resource for the button to display
     * @param resourceId : id for the resource, in res folder
     * @param xPosition : object x-position
     * @param yPosition : object y-position
     * @param xScale : object x-scale
     * @param yScale : object y-scale
     * @param button : true turns the image into a button, false means no onTouchListener
     */
    public void addGraphics(final ImageView imageView, int resourceId, float xPosition, float yPosition, float xScale, float yScale, boolean button) {
        imageView.setImageResource(resourceId);
        this.editGraphics(imageView, xPosition, yPosition, xScale, yScale);
        imageView.setAlpha(0.75f);

        if(button) {
            //Setting up the animation: on action down, grey out the image
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            imageView.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                            imageView.setAlpha(1.0f);
                            imageView.invalidate();
                            break;
                        case MotionEvent.ACTION_UP:
                            imageView.getDrawable().clearColorFilter();
                            imageView.invalidate();
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            imageView.getDrawable().clearColorFilter();
                            imageView.setAlpha(0.75f);
                            imageView.invalidate();
                            break;
                    }
                    return false;
                }
            });
        }
        this.activityLayout.addView(imageView);
    }

    /**
     * @Method
     * @param imageView : Reference to imageview object
     * @param message : message to place on object
     * @param textSize : text size of message
     * @param font : font type of message
     * @param color : font color
     * @param resourceId : resource ID of image view graphic
     * @param xPosition : X position in %
     * @param yPosition : Y position in %
     * @param xScale : x Scale of image view
     * @param yScale : y Scale of image view
     */
    public void addTextToButton(final ImageView imageView, String message, int textSize, String font, String color, int resourceId, float xPosition, float yPosition, float xScale, float yScale){
        this.addGraphics(imageView, resourceId, xPosition, yPosition, xScale, yScale, true);
        this.addText(message, font, color, 2, textSize, xPosition, yPosition);
    }

    /**
     * @Method addGraphicInputBox : programmatic way of adding a text input box on top of a graphic layout
     * @param textInput : EditText object reference
     * @param hint : optional placeholder text
     * @param resourceId : image resource ID
     * @param xPosition : x positon of view in %
     * @param yPosition : y position of view in %
     * @param xScale : x scale
     * @param yScale : y scale
     */
    public void addGraphicInputBox(EditText textInput, String hint, int resourceId, int inputType, float xPosition, float yPosition, float xScale, float yScale){
        this.editGraphics(textInput,xPosition,yPosition,xScale,yScale);
        textInput.setPadding(20,0,20,0);
        textInput.setBackground(this.activityContext.getDrawable(resourceId));
        textInput.setTextColor(Color.BLACK);
        textInput.setInputType(inputType);


        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            textInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        this.activityLayout.addView(textInput);
    }

    /**
     * @Method clearAllEditTexts : Call this method after every activity switch to ensure fields are cleared
     * @param fields : fields that must be cleared
     */
    public void clearAllEditTexts(EditText[] fields){
        for (EditText field : fields){
            field.setText("");
        }
    }

    /**
     * @Method addRadioButtons : adds a list of radio buttons (horizontally)
     * @param listNames : text name for each radio button
     * @param spacing : how much spacing there is between each radio button
     */
    public void addRadioButtons(String[] listNames, float spacing){

    }


    /**
     * Getters and Setters
     */

    /**
     * @Method getTextSize : returns rough estimate of text size in float array (index 0: width, index 1: height)
     * @param message : message
     * @param size : size of text
     * @return float array
     */
    private float[] getTextSize(String message, float size){
        TextView tempText = new TextView(this.activityContext);
        tempText.setText(message);
        tempText.setTextSize(size);

        float[] dimensions = new float[2];
        dimensions[0] = tempText.getWidth()/this.getPhoneWidth();
        dimensions[1] = tempText.getHeight()/this.getPhoneHeight();

        return dimensions;
    }

    public Context getActivityContext(){
        return this.activityContext;
    }

    public RelativeLayout getActivityLayout(){
        return this.activityLayout;
    }

    public int getPhoneHeight() {
        return phoneHeight;
    }

    public int getPhoneWidth() {
        return phoneWidth;
    }
}
