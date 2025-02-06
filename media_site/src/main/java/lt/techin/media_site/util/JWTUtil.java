package lt.techin.media_site.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    private final String key =  "46be0927a4f86577f17ce6d10bc6aa61";

    public String generateToken(String email, List<String> roles) {
        String token = Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 360000))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
        return token;
    }

}
