package idv.victor.sideproject.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * Jwt 相關處理工具
 */
@Component
public class JwtUtils {
    /**
     * jwt secret key striing
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * jwt有效時長
     */
    @Value("${jwt.expired.hour}")
    private int expiredHour;

    /**
     * jwt secret key bytes
     */
    private byte[] secretBytes;

    /**
     * jwt secret key string轉為bytes
     */
    @PostConstruct
    private void generateSecretBytes() {
        secretBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(jwtSecret);
    }

    /**
     * 取得jwt有效時間
     *
     * @param token jwt
     * @return jwt有效時間
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 取得 jwt資訊
     *
     * @param token          jwt
     * @param claimsResolver Function<Claims,T>
     * @param <T>            T
     * @return T
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 取得jwt資訊
     *
     * @param token jwt
     * @return jwt資訊
     */
    public Claims extractClaim(String token) {
        return extractAllClaim(token);
    }

    /**
     * 取得jwt資訊
     *
     * @param token jwt
     * @return jwt資訊
     * @throws ExpiredJwtException 程式異常
     */
    private Claims extractAllClaim(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                   .setSigningKey(generateSecretKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    /**
     * 檢核jwt是否過期
     *
     * @param token jwt
     * @return 檢核結果
     */
    private Boolean isTokenExpired(String token) {

        return LocalDate.now().isAfter(
                Instant.ofEpochMilli(extractExpiration(token).getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * 產生jwt
     *
     * @param loginToken 登入token
     * @param uuid       會員流水號
     * @param expiration 時效(minutes)
     * @return jwt token
     */
    public String generateToken(String loginToken, String uuid, Integer expiration) {
        if (StringUtils.isNotBlank(loginToken)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("name", loginToken);
            return creatToken(claims, uuid, expiration);
        } else {
            if (StringUtils.isNotBlank(uuid)) {
                return creatToken(null, uuid, expiration);

            } else {
                return creatToken(null, "guest", expiration);
            }
        }
    }

    /**
     * 產生jwt
     *
     * @param claims     jwt資訊
     * @param subject    JWT所面向的用戶
     * @param expiration 時效(minutes)
     * @return Token
     */
    public String creatToken(Map<String, Object> claims, String subject, Integer expiration) {
        JwtBuilder jwtBuilder = Jwts.builder();

        if (ObjectUtils.isNotEmpty(claims)) {
            jwtBuilder.setClaims(claims);
        }

        long nowMillis = System.currentTimeMillis();

        if (ObjectUtils.isNotEmpty(expiration)) {
            jwtBuilder.setExpiration(new Date(nowMillis + expiration * 60 * 1000));
        } else {
            jwtBuilder.setExpiration(new Date(nowMillis + 1000L * 60 * 60 * expiredHour));
        }

        return jwtBuilder.setId(UUID.randomUUID().toString())
                         .setSubject(subject)
                         .setIssuedAt(new Date(nowMillis))
                         .signWith(generateSecretKey(), SignatureAlgorithm.HS256)
                         .compact();
    }

    /**
     * 檢核jwt
     *
     * @param token   jwt
     * @param jti     jwt's id
     * @param subject JWT所面向的用戶
     * @return 檢核結果
     */
    public Boolean validateToken(String token, String jti, String subject) {
        if (!StringUtils.equalsIgnoreCase(jti, extractClaim(token, Claims::getId))) {
            throw new IllegalArgumentException("jti invalid");
        }

        if (isTokenExpired(token)) {
            throw new IllegalArgumentException("toke expired");
        }

        if (!StringUtils.equalsIgnoreCase(subject, extractClaim(token, Claims::getSubject))) {
            throw new IllegalArgumentException("subject of jwt invalid");
        }
        return true;
    }

    /**
     * 取得JWT所面向的用戶
     *
     * @param token jwt
     * @return String JWT所面向的用戶
     */
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 檢查jwt是否要更新
     *
     * @param ttlMillis jwt時效
     * @return Boolean 檢查結果
     */
    public Boolean shouldRefreshToken(long ttlMillis) {
        long remainingTime = ttlMillis - System.currentTimeMillis();
        long tokenRefreshTime = (1000L * 60 * 60 * expiredHour) / 2;
        return remainingTime > 0 && remainingTime < tokenRefreshTime;
    }

    /**
     * 從httpServletRequest取出loginToken
     *
     * @param httpServletRequest httpServletRequest
     * @return loginToken
     */
    public String getLoginTokenFromHttpRequest(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String jwtToken = authorization.replace("Bearer ", "");
        String loginToken = extractClaim(jwtToken).get("name", String.class);
        // 若為登入後功能jwt，取出loginToken進行驗證
        if (StringUtils.isNotBlank(loginToken)) {
            return loginToken;
        } else {
            return null;
        }
    }

    /**
     * 取得secretKey
     *
     * @return secretKey
     */
    private SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(secretBytes);
    }

}
