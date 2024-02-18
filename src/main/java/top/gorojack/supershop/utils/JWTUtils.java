package top.gorojack.supershop.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.gorojack.supershop.pojo.User;

import java.util.Date;

@Component
public class JWTUtils {

    private static String SECRET;

    private static Long EXPIRATION;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JWTUtils.SECRET = secret;
    }

    @Value("${jwt.expiration}")
    public void setExpiration(Long expiration) {
        JWTUtils.EXPIRATION = expiration;
    }

    public static String createJWT(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("uid", user.getUid())
                    .withClaim("username", user.getUsername())
                    .withClaim("avatar", user.getAvatar())
                    .withClaim("role", user.getRole())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public static User parseJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            User user = new User();
            user.setUid(jwt.getClaim("uid").asLong());
            user.setUsername(jwt.getClaim("username").asString());
            user.setAvatar(jwt.getClaim("avatar").asString());
            user.setRole(jwt.getClaim("role").asString());
            return user;
        } catch (Exception exception) {
            return null;
        }
    }
}
