package com.gosterici.adesso.userservice.service;

import com.gosterici.adesso.userservice.domain.Authorities;
import com.gosterici.adesso.userservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${spring.security.jwt.secret}")
  private String secretKey;

  @Value("${spring.security.jwt.expiration}")
  private long jwtExpiration;

  public String generateToken(User user) {
    final Map<String, List<String>> authorities =
        Map.of(
            "authorities",
            user.getAuthorities().stream()
                .map(Authorities::getAuthority)
                .collect(Collectors.toList()));
    return Jwts.builder()
        .setClaims(authorities)
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
