package app.classlink.MVP.Presenter;


import android.support.v7.app.AppCompatActivity;
import android.view.View;

abstract public class BaseLogin extends AppCompatActivity implements View.OnClickListener {
    public abstract void onClick(View view);
    public abstract void setFragments();
}
