package com.slamDunkers.SlamStats.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class TockenService {

	public static final String TOKEN_SECRET = "slamDunkersSlamStats2024";
	public static final int EXPIRE_AFTER = 36000;

	public String createToken(int idUser, String role){
		try{
			Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
			String token = JWT.create()
					.withClaim("idUser",idUser)
					.withClaim("role",role)
					.withIssuedAt(Instant.now())
					.withExpiresAt(Instant.now().plus(EXPIRE_AFTER, ChronoUnit.SECONDS))
					.sign(algorithm);
			return token;
		}catch (Exception e){
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}

	}


	public UserToken getUserIdFromToken(String token){
		try{
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			UserToken ut = new UserToken(jwt.getClaim("userId").asInt(), jwt.getClaim("role").asString());
			return ut;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
