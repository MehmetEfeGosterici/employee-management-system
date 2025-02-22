package com.gosterici.adesso.employeeservice.repositories;

import com.gosterici.adesso.employeeservice.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectsRepository extends JpaRepository<Project, UUID> {
}
