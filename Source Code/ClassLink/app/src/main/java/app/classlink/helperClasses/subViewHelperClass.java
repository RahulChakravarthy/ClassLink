package app.classlink.helperClasses;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

;

/**
 * @Class subViewHelperClass : same as viewHelperClass except handles support of different layouts (Linear, Drawer, Constraint etc)
 */
public class subViewHelperClass {

    private Context activityContext;

    private final int phoneWidth;
    private final int phoneHeight;

    public subViewHelperClass(Context context, Display display){
        this.activityContext = context;
        //get Device information
        Point size = new Point();
        display.getSize(size);
        this.phoneWidth = size.x;
        this.phoneHeight = size.y;
    }

    public TextView addText(String message, String fontType, String color, final int positionType, float size, float xPosition, float yPosition){
        final TextView tempText = new TextView(this.activityContext);
        tempText.setText(message);

        Typeface font = Typeface.createFromAsset(this.activityContext.getAssets(), "fonts/" + fontType + ".ttf");
        tempText.setTypeface(font);

        tempText.setTextColor((color == null)? Color.BLACK: Color.parseColor(color));

        tempText.setTextSize((size == 0)? 12: size);

        tempText.setX(xPosition * this.phoneWidth * 0.01f);
        tempText.setY(yPosition * this.phoneHeight * 0.01f);
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
        return tempText;
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
        objectView.setX(xPosition * this.phoneWidth * 0.01f);
        objectView.setY(yPosition * this.phoneHeight * 0.01f);

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
     * @Method addGraphics : Take an ImageView, give it a resource to display, turn it into a button along with up/down animation, and add it to the Activity screen
     * @param imageView : resource for the button to display
     * @param resourceId : id for the resource, in res folder
     * @param xPosition : object x-position
     * @param yPosition : object y-position
     * @param xScale : object x-scale
     * @param yScale : object y-scale
     * @param button : true turns the image into a button, false means no onTouchListener
     */
    public void addGraphics(ViewGroup layout,final ImageView imageView, int resourceId, float xPosition, float yPosition, float xScale, float yScale, boolean button) {
        imageView.setImageResource(resourceId);
        this.editGraphics(imageView, xPosition, yPosition, xScale, yScale);

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
        layout.addView(imageView);
    }

    /**
     * @Method addGraphicInputBox : programmatic way of adding a text input box on top of a graphic layout
     * @param hint : optional placeholder text
     * @param resourceId : image resource ID
     * @param textInput : EditText object reference
     * @param inputType : input mode type
     * @param size : size of text in edit text box
     * @param xPosition : x position of view in %
     * @param yPosition : y position of view in %
     * @param xScale : x scale
     * @param yScale : y scale
     */
    public void addGraphicInputBox(ViewGroup layout, String hint, Integer resourceId, EditText textInput, int inputType, float size, float xPosition, float yPosition, float xScale, float yScale){
        //create background input image only if resource Id isn't null
        if (resourceId != null){
            ImageView background = new ImageView(this.activityContext);
            this.addGraphics(layout, background, resourceId, xPosition, yPosition, xScale, yScale, false);
        }
        //create edit text
        this.editGraphics(textInput,xPosition,yPosition,1,1);
        textInput.setPadding(20,0,20,0);
        textInput.setInputType(inputType);
        textInput.setWidth(Math.round(xScale*this.phoneWidth));
        textInput.setBackgroundColor(Color.TRANSPARENT);
        textInput.setTextColor(Color.BLACK);
        textInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        textInput.setHint((hint != null)? hint : "");
        textInput.setHintTextColor(Color.parseColor("#8EC2F4"));

        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
            textInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        layout.addView(textInput);
    }

    /**
     * @Method isEditTextEmpty : checks to see if any of the inputted edit texts are empty
     * @param inputs : Edit text inputs that need to be verified
     * @return boolean returns true if not empty, false if otherwise
     */
    public boolean isEditTextEmpty(ArrayList<EditText> inputs){
        for (EditText input : inputs){
            String cleanedInput = input.getText().toString().trim();
            if (cleanedInput.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
