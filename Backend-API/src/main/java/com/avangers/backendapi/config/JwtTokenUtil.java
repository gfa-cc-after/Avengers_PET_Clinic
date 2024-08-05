package com.avangers.backendapi.config;

import com.avangers.backendapi.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.token.validity}")
  private long tokenValidity;

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // Retrieve email from JWT token
  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  // Retrieve expiration date from JWT token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // For retrieving any information from token we will need the secret key
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  // Check if the token has expired
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  // Generate token for user
  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, user.getEmail());
  }

  // While creating the token -
  // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
  // 2. Sign the JWT using the HS512 algorithm and secret key.
  // 3. According to JWS Compact Serialization (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
  //    compaction of the JWT to a URL-safe string
  private String doGenerateToken(Map<String, Object> claims, String subject) {

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
            .compact();
  }

  // Validate token
  public Boolean validateToken(String token, User user) {
    final String email = getEmailFromToken(token);
    return (email.equals(user.getEmail()) && !isTokenExpired(token));
  }
}