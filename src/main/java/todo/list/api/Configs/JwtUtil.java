package todo.list.api.Configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import todo.list.api.Dtos.UserDto;

import java.util.Date;

public class JwtUtil {
  static Dotenv env = Dotenv.configure().load();
  private static final String SECRET = env.get("JWT_SECRET_KEY");
  // El JWT servirá durante un dia
  private static final long EXPIRATION_TIME = 86400000;

  public static String generateToken(UserDto userDto) {
    return JWT.create()
        .withSubject(userDto.getId().toString())
        .withClaim("username", userDto.getUsername())
        .withClaim("email", userDto.getEmail())
        .withClaim("admin", userDto.getAdmin())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(Algorithm.HMAC256(SECRET));
  }
}
