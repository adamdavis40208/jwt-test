import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import util.TokenUtil;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.UNAUTHORIZED;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static util.AppConstants.BA_HEADER_NAME;

/**
 * Created by adavis on 6/12/16.
 */
public class LoginControllerTest extends WithApplication {

    @Test
    public void testPositiveLoginBasicAuth(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/login");
        request.header(BA_HEADER_NAME,"Basic ZG9udDpkZWNvZGVtZWJybw==");
        Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testSecureEndpoint(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/dosomething");
        request.cookie(TokenUtil.setJwtTokenInCookie());
        Result result = route(request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testBadLoginBasicAuth(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/login");

        Result result = route(request);
        assertEquals(UNAUTHORIZED, result.status());
    }
}
