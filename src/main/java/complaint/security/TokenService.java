package complaint.security;

import complaint.model.user.User;
import complaint.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 1L;
    private static final String SECRET = SecretGenerator.getNewSecret();
    private static final String ALGORITHM = "HS512";
    private static final String TOKEN_TYPE = "JWT";
    private static final String HEADER_STRING = "AUTH-TOKEN";

    @Autowired
    private UserService userService;

    public UserAuthentication getUserAuthentication(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER_STRING);
        if(token != null) {
            User user = getUserFromToken(token);
            if(user != null)
                return new UserAuthentication(user);
        }
        return null;
    }

    private User getUserFromToken(String token) {
        String email = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
        return userService.getUserByEmail(email);
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(EXPIRATION_TIME));
        System.out.println("Secret: " + SECRET);

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setHeaderParam("alg", ALGORITHM)
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }

}
