package com.gosterici.adesso.employeeservice.domain.requests;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProjectContributorsRequest {
  private List<UUID> employeesToBeAdded;
  private List<UUID> employeesToBeRemoved;
}
