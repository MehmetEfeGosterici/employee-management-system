package com.gosterici.adesso.userservice.domain.resource;

import java.util.List;
import lombok.Data;

@Data
public class UserResource {

  private String id;
  private String email;
  private String username;
  private String password;
  private List<String> authorities;
}
