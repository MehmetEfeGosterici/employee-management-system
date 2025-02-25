package com.gosterici.adesso.userservice.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private static final String COMMA_SEPARATOR = ",";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String userEmail = request.getHeader("X-Username");
    final String authorization = request.getHeader("X-authorities");

    if (userEmail == null || authorization == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null) {
        List<SimpleGrantedAuthority> authorities =
            Stream.of(authorization.split(COMMA_SEPARATOR))
                .map(SimpleGrantedAuthority::new)
                .toList();
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userEmail, null, authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
      filterChain.doFilter(request, response);
    } catch (Exception exception) {
      logger.warn("Error occurred while authenticating", exception);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response
          .getWriter()
          .write(String.format("Authentication failed: %s", exception.getMessage()));
    }
  }
}
