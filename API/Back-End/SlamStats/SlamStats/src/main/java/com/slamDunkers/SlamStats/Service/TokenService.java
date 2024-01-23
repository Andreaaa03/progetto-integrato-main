package com.slamDunkers.SlamStats.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.slamDunkers.SlamStats.Entity.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * This service class provides methods for creating and decoding JWT tokens.
 */
@Service
@Slf4j
public class TokenService {
    private static final String TOKEN_SECRET = "slamDunkersSlamStats2024";
    public static final int EXPIRE_AFTER = 3600000;

    /**
     * This method creates a JWT token for a user.
     *
     * @param idUser The ID of the user for whom the token is being created.
     * @param role   The role of the user for whom the token is being created.
     * @return A string representing the JWT token.
     * The method uses the HMAC256 algorithm and the TOKEN_SECRET to create the token.
     * The token includes claims for the user's ID and role.
     * The token is issued at the current time and expires after EXPIRE_AFTER seconds.
     * If an error occurs during token creation, the method logs the error message and returns "token non valido".
     */
    public String createToken(int idUser, String role) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.create()
                    .withClaim("idUser", idUser)
                    .withClaim("role", role)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(EXPIRE_AFTER, ChronoUnit.SECONDS))
                    .sign(algorithm);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "token non valido";
        }
    }

    /**
     * This method decodes a JWT token and retrieves the user's ID and role from it.
     *
     * @param token The JWT token to be decoded.
     * @return A UserToken object containing the user's ID and role.
     * The method uses the HMAC256 algorithm and the TOKEN_SECRET to verify the token.
     * It then retrieves the user's ID and role from the token's claims and returns them in a UserToken object.
     * If an error occurs during token decoding, the method logs the error message and returns null.
     */
    public UserToken getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return new UserToken(jwt.getClaim("idUser").asInt(), jwt.getClaim("role").asString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
