package com.gosterici.adesso.userservice.service.ports;

import com.gosterici.adesso.userservice.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserQuery {

    Optional<User> getUserById(UUID userId);

    List<User> getUsers(List<UUID> userIds);

    List<User> getAllUsers();
}
