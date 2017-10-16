package app.classlink.MVP.Presenter;

import android.os.Bundle;
import android.view.View;

import app.classlink.MVP.Model.LoginUI;
import app.classlink.MVP.View.LoginHandler;
import app.classlink.R;

/**
 * @Presenter Login : handles all UI updates and displays I/O from the corresponding view handler
 */
public class Login extends BaseLogin implements LoginUI {

    public LoginHandler loginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    }

    //Set up fragments here with other UI
    @Override
    public void setFragments() {

    }

    @SuppressWarnings("ALL")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_lecture_create:
                startLogin();
                break;
            //And so on
            default:
                break;
        }
    }



    @Override
    public void startLogin() {
        loginHandler.startLogin(/*pass parameters here*/);
    }
}
