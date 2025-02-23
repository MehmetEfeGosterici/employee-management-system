package com.gosterici.adesso.userservice.controller;

import com.gosterici.adesso.userservice.domain.User;
import com.gosterici.adesso.userservice.domain.request.LoginRequest;
import com.gosterici.adesso.userservice.domain.request.SignUpRequest;
import com.gosterici.adesso.userservice.service.AuthenticationService;
import com.gosterici.adesso.userservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final JwtService jwtService;

  @PostMapping("/signup")
  public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
    authenticationService.signUp(signUpRequest);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    User authenticatedUser = authenticationService.authenticate(loginRequest);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    return ResponseEntity.ok(jwtToken);
  }
}
