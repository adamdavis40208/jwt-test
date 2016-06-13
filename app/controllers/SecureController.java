package controllers;

import play.mvc.Result;
import play.mvc.Security;
import security.JwtAuthenticator;

import static play.mvc.Results.ok;

/**
 * Created by adavis on 6/12/16.
 */
public class SecureController {


    @Security.Authenticated(JwtAuthenticator.class)
    public Result doSomething(){
        return ok();
    }
}
