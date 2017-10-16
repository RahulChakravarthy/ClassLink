package app.classlink.MVP.Model;


/**
 * @Interface ValidateLogin : Model that all activity handlers should implement while dealing with logging in
 */
public interface ValidateLogin {

    interface CheckCredentials {

        void checkEmail(String email);
        void checkPassword(String password);
    }

    void startLogin();
    boolean emptyInputs(String[] inputs);
}
