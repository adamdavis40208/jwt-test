import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.TokenUtil;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by adavis on 6/12/16.
 */
@RunWith(Parameterized.class)
public class TokenUtilTest {

    @Parameterized.Parameters
    public static Collection<Object[]> usernameData() {
        return Arrays.asList(new Object[][] {
                {"Basic ZG9udDpkZWNvZGVtZWJybw==","dont","decodemebro"}, {"ZG9udDpkZWNvZGVtZWJybw==", "","" }, { "akljsdfglasdfguk2343", "","" }, { "", "","" }, { null, "","" }
        });
    }

    @Parameterized.Parameter
    public String data;

    @Parameterized.Parameter(value = 1)
    public String username;

    @Parameterized.Parameter(value = 2)
    public String password;

    @Test
    public void testBasicAuthUsername(){
        assert(TokenUtil.processBasicAuthTokenUsername(data).equals(username));
    }

    @Test
    public void testBasicAuthPassword(){
        assert(TokenUtil.processBasicAuthTokenPassword(data).equals(password));
    }
}
