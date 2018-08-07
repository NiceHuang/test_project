package cn.hnx.common.utils;

import cn.hnx.common.bean.DataPortralUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class TokenUtil {
    private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000;
    private static final String AUTH_HEADER_NAME = "token";
    private static String secret = "data_portral";


    public static Claims verifyToken(HttpServletRequest request){
        try {
            String token = request.getHeader(AUTH_HEADER_NAME);
            if (token == null && StringUtils.isEmpty(token)){
                return null;
            }
            token = token.replace("Bearer","").trim();
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
