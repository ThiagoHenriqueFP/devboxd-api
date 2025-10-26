package io.devboxd.boxd_api.infrastructure.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.devboxd.boxd_api.infrastructure.config.userDetails.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String secret = "umfodendosegredoessachavetemquesergrandeparadificultarbruteforcemasachoquejaestamtgrande";
    private static final String issuer = "devboxd-main-api";

    public String generateToken(UserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Fortaleza")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Fortaleza")).plusHours(4).toInstant();
    }

}
