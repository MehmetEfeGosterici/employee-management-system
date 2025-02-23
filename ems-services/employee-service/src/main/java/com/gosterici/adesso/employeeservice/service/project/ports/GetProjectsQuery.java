package com.gosterici.adesso.employeeservice.service.project.ports;

import com.gosterici.adesso.employeeservice.domain.Project;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetProjectsQuery {

  List<Project> getProjects();

  Optional<Project> getProject(UUID projectId);
}
