package controllers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import play.mvc.Controller;
import play.mvc.Result;
import security.Security;
import util.TokenUtil;

/**
 * Created by adavis on 6/12/16.
 */
public class LoginController extends Controller {

    @Inject
    @Named("Basic Auth")
    private Security basicAuthSecurity;


    public Result loginBasicAuth() {
        if(!basicAuthSecurity.validateUsernamePassword(request())){
            return unauthorized();
        }
        session().clear();
        response().setCookie(TokenUtil.setJwtTokenInCookie());
        return ok();
    }

}
