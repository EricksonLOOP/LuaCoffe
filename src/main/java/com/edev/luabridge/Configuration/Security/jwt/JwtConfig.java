package com.edev.luabridge.Configuration.Security.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    private final String secret_key = "zFj5rXLeDDhIzYK1rrkeDVVhPqkLokZzDRqEydK8i9W9qOc4haJ7ifzS0bkDVy4jzbVbtRKkATg7lZAMwhJDrtUx4dhCYyK3FkYpie0SLZkzPKfBymBOrMYjgu8lLjTmaHjZku4Jv5QSyO4Op6fog0F5Fuhebtx6Qht99waIAMmHLsgLA6FBHFDz9vMAvrTgAHobme6U";
    @Bean
    public JwtParser jwtParser(){
    return Jwts.parser().setSigningKey(secret_key).build();
    }

}
