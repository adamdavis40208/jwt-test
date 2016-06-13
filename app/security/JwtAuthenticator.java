package security;

import play.mvc.*;
import util.TokenUtil;

/**
 * Created by adavis on 6/12/16.
 */
public class JwtAuthenticator extends play.mvc.Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        boolean result = getTokenFromHeader(ctx);
        if (result) {
            //TODO refactor jwt processor to return subject name
            return "bob";
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

    private boolean getTokenFromHeader(Http.Context ctx) {
        Http.Cookie jwtCookie = ctx.request().cookie("jwt");
        return TokenUtil.validateJwtTokenInSession(jwtCookie);
    }
}
