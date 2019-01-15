package group.bridge.web.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtBuilder {
    public static String buildToken(String userName){
        Date now = new Date(System.currentTimeMillis());
        SecretKey secretKey=buildSecret();
        io.jsonwebtoken.JwtBuilder jwt=Jwts.builder().setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                .setIssuedAt(now)
                .claim("name",userName)
                .setIssuer("group4")
                .signWith(SignatureAlgorithm.HS256,secretKey);
        return jwt.compact();
    }
    public static SecretKey buildSecret(){
        String stringKey ="bridgeSecret";
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
