package app.classlink.MVP.View;

import app.classlink.MVP.Model.ValidateLogin;


/**
 * @View LoginHandler : handles business logic regarding logging in
 */
public class LoginHandler implements ValidateLogin, ValidateLogin.CheckCredentials{

    @Override
    public void startLogin() {

    }

    @Override
    public boolean emptyInputs(String[] inputs) {
        for (String input : inputs){
            if (input.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public void checkEmail(String email) {
        //check email logic
    }

    @Override
    public void checkPassword(String password) {

    }
}
