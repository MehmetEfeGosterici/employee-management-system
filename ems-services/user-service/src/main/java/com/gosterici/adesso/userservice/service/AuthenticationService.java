package com.gosterici.adesso.userservice.service;

import com.gosterici.adesso.userservice.domain.User;
import com.gosterici.adesso.userservice.domain.request.LoginRequest;
import com.gosterici.adesso.userservice.domain.request.SignUpRequest;
import com.gosterici.adesso.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  public void signUp(SignUpRequest signUpRequest) {
    userRepository
        .findByEmail(signUpRequest.getEmail())
        .ifPresent(
            user -> {
              throw new IllegalArgumentException("User already exists");
            });

    final User user =
        User.builder()
            .email(signUpRequest.getEmail())
            .username(signUpRequest.getUsername())
            .password(signUpRequest.getPassword())
            .build();
    userRepository.save(user);
  }

  public User authenticate(LoginRequest loginRequest) {
    final String email = loginRequest.getEmail();
    final String password = loginRequest.getPassword();
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    return userRepository
        .findByEmail(email)
        .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
  }
}
