package com.sast.woc.Utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;

public class TokenUtil {
    private static String TOKEN="I_AM_A_TOKEN";

    //解析token
    public static DecodedJWT tokenVerify(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    //获取token
    public static String getToken(String userName,Integer role){
        JWTCreator.Builder jb=JWT.create();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        jb.withClaim("userName",userName)
          .withClaim("role",role)
          .withExpiresAt(instance.getTime());
        return jb.sign(Algorithm.HMAC256(TOKEN));
    }


}
