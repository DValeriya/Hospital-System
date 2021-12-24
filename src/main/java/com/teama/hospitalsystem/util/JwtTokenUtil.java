package com.teama.hospitalsystem.util;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Repository
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private String jwtSecret;

    public JwtTokenUtil() {
        this.jwtSecret = "hospitalSystemSpringRest";
    }

    public String getSecret() {
        return jwtSecret;
    }


    public String generateToken(UserDetails userDetails) {
        Date exp = Date.from(LocalDateTime.now().plusDays(3)
                .atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String extractLogin(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractLogin(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (ExpiredJwtException expEx) {
            LOGGER.error("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            LOGGER.error("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            LOGGER.error("Malformed jwt");
        } catch (SignatureException sEx) {
            LOGGER.error("Invalid signature");
        } catch (Exception e) {
            LOGGER.error("invalid token");
        }
        return false;
    }
}
