package com.gosterici.adesso.userservice.domain.request;

import java.util.Set;
import java.util.UUID;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
  private UUID userId;
  private String username;
  private Set<String> authoritiesToAdd;
  private Set<String> authoritiesToRevoke;
}
