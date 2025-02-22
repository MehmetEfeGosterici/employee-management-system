package com.gosterici.adesso.userservice.domain.request;

import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class UpdateUserRequest {
    private UUID userId;
    private String username;
    private Set<String> authoritiesToAdd;
    private Set<String> authoritiesToRevoke;
}
