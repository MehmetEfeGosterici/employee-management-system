package com.gosterici.adesso.userservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authorities extends BaseEntity {

  private String authority;
  @Builder.Default private boolean isActive = true;

  @ManyToMany(mappedBy = "authorities")
  private Set<User> users;

  public void addUsers(Set<User> users) {
    this.users.addAll(users);
  }

  public void removeUsers(Set<User> users) {
    this.users.removeAll(users);
  }
}
