package com.gosterici.adesso.employeeservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity {
  private String title;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  private ProjectState status;

  @ManyToMany(mappedBy = "projects")
  private List<Employee> employees;

  public void addProjectContributors(List<Employee> employees) {
    this.employees.addAll(employees);
  }

  public void removeProjectContributors(List<Employee> employees) {
    this.employees.removeAll(employees);
  }
}
