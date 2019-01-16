package group.bridge.web.util;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    public static boolean validateToken(HttpServletRequest request){
        boolean result=false;
        String token= CookieUtil.getCookie(request,"token");
        if(token!=null){
            Claims claims= JwtBuilder.getClaims(token);
            String name=claims.get("name",String.class);
            System.out.println(name);
            result=true;
        }
        return result;
    }
}
