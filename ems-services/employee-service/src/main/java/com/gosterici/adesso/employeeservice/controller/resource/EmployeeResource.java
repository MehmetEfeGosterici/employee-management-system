package com.gosterici.adesso.employeeservice.controller.resource;

import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeResource {
  private String name;
  private String surname;
  private String email;
  private String phoneNumber;
  private Set<EmployeeRole> employeeRoles;
  private Set<EmployeeAddress> employeeAddresses;
  private List<EmployeeProject> employeeProjects;

  @Getter
  @Setter
  public static class EmployeeRole {
    private String roleName;
    private boolean isActive;
  }

  @Getter
  @Setter
  public static class EmployeeAddress {
    private String street;
    private String city;
    private String country;
  }

  @Getter
  @Setter
  public static class EmployeeProject {
    private String title;
    private String description;
    private String status;
  }
}
