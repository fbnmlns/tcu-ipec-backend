package cr.go.mep.gradingsystem.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import cr.go.mep.gradingsystem.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class TokenProvider {
    @Value("${spring.security.jwt.token.secret-key}")
    private String JWT_SECRET;

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName())
                    .withClaim("lastName", user.getLastName())
                    .withClaim("email", user.getEmail())
                    .withClaim("username", user.getUsername())
                    .withClaim("role", user.getRole().getValue())
                    .withExpiresAt(generateAccessExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new JWTCreationException("Could not generate access token", e);
        }
    }

    public String validateAccessToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Could not verify access token", e);
        }
    }

    private Instant generateAccessExpirationDate() {
        return ZonedDateTime.now(ZoneOffset.of("-03:00")).plusHours(2).toInstant();
    }
}
