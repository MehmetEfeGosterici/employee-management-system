package com.gosterici.adesso.employeeservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Role extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private EmployeeRole role;

  @Builder.Default private boolean isActive = true;

  @ManyToOne
  private Employee employee;
}
