package com.gosterici.adesso.userservice.repository;

import com.gosterici.adesso.userservice.domain.Authorities;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, UUID> {

  Set<Authorities> findByAuthorityIn(Set<String> authorities);
}
