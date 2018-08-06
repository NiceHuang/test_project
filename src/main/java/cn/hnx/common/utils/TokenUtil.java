package cn.hnx.common.utils;

import cn.hnx.common.bean.DataPortralUser;
import cn.hnx.common.bean.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;


public class TokenUtil {
    private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000;
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static String secret = "mrin";


    public static Optional<Authentication> verifyToken(HttpServletRequest request){
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null && !token.isEmpty()){
            final DataPortralUser user = parseUserFromToken(token.replace("Bearer","").trim());
            return Optional.of(new UserAuthentication(user));
        }
        return Optional.empty();
    }

    /**
     * 根据token获取user信息
     * @param token
     * @return
     */
    public static DataPortralUser parseUserFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        int id = (int) claims.get("userId");
        DataPortralUser user = new DataPortralUser();
        user.setId(id);
        return user;
    }
    /**
     * 生成token
     * @param user
     * @return
     */
    public static String createTokenForUser(DataPortralUser user){
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .claim("createTime", System.currentTimeMillis())
                .claim("userId", user.getId()).signWith(SignatureAlgorithm.HS256,secret).compact();
    }
}
