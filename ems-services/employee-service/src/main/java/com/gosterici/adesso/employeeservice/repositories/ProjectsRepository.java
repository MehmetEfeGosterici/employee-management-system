package com.gosterici.adesso.employeeservice.repositories;

import com.gosterici.adesso.employeeservice.domain.Project;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Project, UUID> {}
