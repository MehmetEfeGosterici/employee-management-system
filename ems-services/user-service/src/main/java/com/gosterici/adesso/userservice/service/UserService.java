package com.gosterici.adesso.userservice.service;

import com.gosterici.adesso.userservice.domain.Authorities;
import com.gosterici.adesso.userservice.domain.User;
import com.gosterici.adesso.userservice.domain.request.UpdateUserRequest;
import com.gosterici.adesso.userservice.repository.AuthoritiesRepository;
import com.gosterici.adesso.userservice.repository.UserRepository;
import com.gosterici.adesso.userservice.service.ports.DeleteUserPort;
import com.gosterici.adesso.userservice.service.ports.UpdateUserPort;
import com.gosterici.adesso.userservice.service.ports.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserQuery, UpdateUserPort, DeleteUserPort {

  private final UserRepository userRepository;
  private final AuthoritiesRepository authoritiesRepository;
  private final PasswordEncoder passwordEncoder;

  public Optional<User> getUserById(UUID userId) {
    return userRepository.findById(userId);
  }

  @Override
  public List<User> getUsers(List<UUID> userIds) {
    return userRepository.findAllById(userIds);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void updateUser(UpdateUserRequest request) {
    Optional<User> optionalUser = userRepository.findById(request.getUserId());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setUsername(request.getUsername());

      grantAuthorities(user, request.getAuthoritiesToAdd());
      revokeAuthorities(user, request.getAuthoritiesToRevoke());
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }

  private void grantAuthorities(User user, Set<String> authoritiesToAdd) {
    Set<Authorities> authorities = authoritiesRepository.findByAuthorityIn(authoritiesToAdd);
    authorities.forEach(authority -> authority.addUsers(Set.of(user)));
    user.grantAuthorities(authorities);
  }

  private void revokeAuthorities(User user, Set<String> authoritiesToRevoke) {
    Set<Authorities> authorities = authoritiesRepository.findByAuthorityIn(authoritiesToRevoke);
    authorities.forEach(authority -> authority.removeUsers(Set.of(user)));
    user.revokeAuthorities(authorities);
  }

  @Override
  public void deleteUser(UUID userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      userRepository.delete(user);
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }
}
