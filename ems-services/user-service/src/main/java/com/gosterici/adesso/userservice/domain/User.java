package com.gosterici.adesso.userservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
public class User extends BaseEntity {

  private String email;
  private String username;
  private String password;

  @ManyToMany
  @JoinTable(
      name = "user_authorities",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id"))
  private Set<Authorities> authorities;

  public void grantAuthorities(Set<Authorities> authorities) {
    this.authorities.addAll(authorities);
  }

  public void revokeAuthorities(Set<Authorities> authorities) {
    this.authorities.removeAll(authorities);
  }
}
