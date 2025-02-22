package com.gosterici.adesso.userservice.domain.request;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String email;
    private String username;
    private String password;
}
