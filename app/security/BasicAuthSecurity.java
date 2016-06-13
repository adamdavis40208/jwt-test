package security;

import play.mvc.Http;
import util.TokenUtil;

import static util.AppConstants.BA_HEADER_NAME;

/**
 * Created by adavis on 6/12/16.
 */
public class BasicAuthSecurity extends Security {

    public void setUsername(Http.Request request){
       setUsername(TokenUtil.processBasicAuthTokenUsername(request.getHeader(BA_HEADER_NAME)));
    }

    public void setPassword(Http.Request request){
       setPassword(TokenUtil.processBasicAuthTokenPassword(request.getHeader(BA_HEADER_NAME)));
    }

    public void validateData(){
        //for now assuming if they're not null, it's valid. Super secure
        if(getUsername()!=null && getPassword()!=null){
            setValidPassword(true);
            setValidUsername(true);
        }
    }
}
