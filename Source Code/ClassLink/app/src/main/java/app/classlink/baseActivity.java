package app.classlink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import app.classlink.helperClasses.*;

/**
 * Created by jaywe on 2017-06-04.
 */

public class baseActivity {
    protected RelativeLayout loginLayout;
    protected viewHelperClass viewHelperClass;

    public void changeActivity(final ImageView button, final Class destination) {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(button.getContext(), destination.getClass()).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                button.getContext().startActivity(i);
            }
        });
    }
}
