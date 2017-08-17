package app.classlink.helperClasses;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;;
import android.view.Display;
import android.widget.TextView;

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
}
