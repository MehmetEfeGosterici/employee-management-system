package com.gosterici.adesso.employeeservice.service.project.ports;

import com.gosterici.adesso.employeeservice.domain.ProjectState;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

public interface CreateProjectUseCase {

  void createProject(CreateProjectCommand command);

  @Getter
  @Builder
  class CreateProjectCommand {
    private String title;
    private String description;
    private LocalDate startDate;
    private ProjectState status;
    private List<UUID> employeeIds;
  }
}
