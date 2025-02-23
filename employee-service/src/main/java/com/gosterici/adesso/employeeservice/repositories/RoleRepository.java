package com.gosterici.adesso.employeeservice.repositories;

import com.gosterici.adesso.employeeservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {}
