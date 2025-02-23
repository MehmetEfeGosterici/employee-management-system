package com.gosterici.adesso.employeeservice.service.project.ports;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

public interface UpdateProjectUseCase {

  void updateProjectContributors(UpdateProjectContributorsCommand command);

  @Getter
  @Builder
  class UpdateProjectContributorsCommand {
    private UUID projectId;
    private List<UUID> employeesToBeAdded;
    private List<UUID> employeesToBeRemoved;
  }
}
