package com.gosterici.adesso.userservice.domain.request;

import lombok.Getter;

@Getter
public class LoginRequest {

  private String email;
  private String password;
}
