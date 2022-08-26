package com.skaypal.ebay_clone.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JWTUtil {
    private final String SECRET = "WABALABADUBDUB";

    public String generateToken(User user) {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("userId",user.getId())
                .withClaim("username",user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.from(Instant.now().plusSeconds( 60*60))))
                .withIssuer("Skaypal corp")
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateTokenAndRetrieveSubject(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject("User Details")
                .withIssuer("Skaypal corp")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

    public Integer retrieveUserId(String token) {
        return JWT.decode(token.substring(7)).getClaim("userId").asInt();

    }
}
