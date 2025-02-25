package com.gosterici.adesso.employeeservice.infrastructure.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final static String COMMA_SEPARATOR = ",";
    private final static String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String userEmail = request.getHeader("X-Username");
        final String authorization = request.getHeader("X-Authorities");

        if (userEmail == null || authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                List<SimpleGrantedAuthority> authorities = Stream.of(authorization.split(COMMA_SEPARATOR))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userEmail, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            logger.warn("Error occurred while authenticating", exception);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(String.format("Authentication failed: %s", exception.getMessage()));
        }
    }
}
