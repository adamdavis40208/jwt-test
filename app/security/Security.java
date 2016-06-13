package security;

import play.mvc.Http;

/**
 * Created by adavis on 6/12/16.
 */
public abstract class Security {

    private String username;

    private String password;

    private boolean validUsername;

    private boolean validPassword;

    public boolean validateUsernamePassword(Http.Request request){
        setUsername(request);
        setPassword(request);
        validateData();
        return isValid();
    }

    protected abstract void setUsername(Http.Request request);

    protected abstract void setPassword(Http.Request request);

    protected abstract void validateData();

    private boolean isValid(){
        return isValidUsername() && isValidPassword();
    }

    void setUsername(String username) {
        if (username != null && username.length() >1)
        this.username = username;
    }

    String getUsername(){
        return this.username;
    }

    void setPassword(String password) {
        if(password != null && password.length()>0)
        this.password = password;
    }

    String getPassword(){
        return this.password;
    }

    private boolean isValidUsername() {
        return validUsername;
    }

    void setValidUsername(boolean validUsername) {
        this.validUsername = validUsername;
    }

    private boolean isValidPassword() {
        return validPassword;
    }

    void setValidPassword(boolean validPassword) {
        this.validPassword = validPassword;
    }
}
