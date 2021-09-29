package com.revature.feed.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  A utility class for JSON Web Tokens.
 *
 *  @see <a href=https://github.com/auth0/java-jwt>https://github.com/auth0/java-jwt</a>
 */
@Component
public class JwtUtility {
    public static final String SECRET = "Ocean-Kevin-Child";
    public static final Algorithm algorithm = Algorithm.HMAC256(SECRET);
    public static final JWTVerifier verifier = JWT.require(algorithm).build();

//    Method for verifying that a given token is valid or not.
    public DecodedJWT verify(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
        }
        return null;
    }

}