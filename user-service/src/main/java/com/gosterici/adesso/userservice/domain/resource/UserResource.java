package com.gosterici.adesso.userservice.domain.resource;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class UserResource {

    private String id;
    private String email;
    private String username;
    private String password;
    private List<String> authorities;
}
