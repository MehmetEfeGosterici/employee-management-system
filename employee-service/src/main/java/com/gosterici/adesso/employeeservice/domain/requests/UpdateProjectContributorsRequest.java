package com.gosterici.adesso.employeeservice.domain.requests;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class UpdateProjectContributorsRequest {
    private List<UUID> employeesToBeAdded;
    private List<UUID> employeesToBeRemoved;
}
