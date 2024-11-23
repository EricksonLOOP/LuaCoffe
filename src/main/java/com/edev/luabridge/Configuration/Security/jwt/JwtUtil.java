package com.edev.luabridge.Configuration.Security.jwt;

import com.edev.luabridge.Entities.UserEntity.UserEntity;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
   private long accesstokenValidity = 60*60*1000;
    private final String secret_key = "zFj5rXLeDDhIzYK1rrkeDVVhPqkLokZzDRqEydK8i9W9qOc4haJ7ifzS0bkDVy4jzbVbtRKkATg7lZAMwhJDrtUx4dhCYyK3FkYpie0SLZkzPKfBymBOrMYjgu8lLjTmaHjZku4Jv5QSyO4Op6fog0F5Fuhebtx6Qht99waIAMmHLsgLA6FBHFDz9vMAvrTgAHobme6U";

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    public JwtUtil(JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }
    public String createToken(UserEntity user){
        ClaimsBuilder claims = Jwts.claims().setSubject(user.getEmail());
        claims.add("name", user.getName());
        claims.add("id", user.getId());
        claims.add("password", user.getPassword());
        Date tokenCreateTime = new Date();
        Date tokenValidty = new Date(tokenCreateTime.getTime()+TimeUnit.MINUTES.toMillis(accesstokenValidity));
        return Jwts.builder()
                .setClaims(claims.build())
                .setExpiration(tokenValidty)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();

    }
    private Claims parseJwtClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }
}
