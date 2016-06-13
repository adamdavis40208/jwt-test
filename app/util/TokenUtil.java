package util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import play.mvc.Http;

import java.util.Base64;

/**
 * Created by adavis on 6/12/16.
 */
public class TokenUtil {

    //this is best stored somewhere safe. That varies by architecture.
    private static final byte[] key = "jiikBXstOlArDXO8YuvUhHgThMbeZ3XTb8kBn4tL-uoHWoR2p-zB3VB6XwMi9dQqrqlqXY55ZASRJIht2sw9pKeKHrd4De9kA_2V24SddcUk9vIug5zfTFDheUS-OUKzwXNtg2s72D8H-JgMDH63qGZxCbt86VilH0Az_BHdiCkfDy4a8F5AZ4FDABHwMgmj_NqNMPn7hqO6f0iWX3bkqqDAZToIfx4SEUbuFywFa1SpE9rrhO7a4sWc1_JPJR8Dz7XbuIqRfpvYDxXcTMTLZrAbe8JFTcWhWXZsULaAgdlQy33Z76QJLcYEYsx-P5sy5YGXZDzka_KiUtFL8UIObA".getBytes();

    public static String processBasicAuthTokenUsername(String token){
       return processBasicAuth("USERNAME",token);
    }

    public static String processBasicAuthTokenPassword(String token){
        return processBasicAuth("PASSWORD",token);
    }

    private static String processBasicAuth(String part, String token){
        String username;
        String password;

        try{
            //example Basic header: Authorization: Basic YWRhbTphZGFt
            if(token == null || token.length()==0){
                return "";
            }
            if(!token.contains("Basic ")){
                return "";
            }
            String base64data = token.split("Basic ")[1];
            String unbased64 = new String(Base64.getDecoder().decode(base64data));
            if(unbased64.split(":").length!=2){
                return "";
            }
            username = unbased64.split(":")[0];
            password = unbased64.split(":")[1];
        }catch(IllegalArgumentException iae){
            //Currently leaving the fields null/empty will cause the system to reject this request
            return "";
        }

        switch(part){
            case "USERNAME":
                return username;
            case "PASSWORD":
                return password;
            default:
                throw new AssertionError("Todo: rework into compilation error");
        }

    }
    public static Http.Cookie setJwtTokenInCookie(){

        String jwt = Jwts.builder().setSubject("jwt").signWith(SignatureAlgorithm.HS512, key).compact();

        return new Http.Cookie("jwt",jwt,3000,"/","localhost",false,false);
    }

    public static boolean validateJwtTokenInSession(Http.Cookie cookie){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(cookie.value()).getBody().getSubject().equals("jwt");

            return true;
        }catch (SignatureException se){
            return false;
        }

    }
}
