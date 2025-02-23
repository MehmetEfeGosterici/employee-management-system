package com.gosterici.adesso.employeeservice.domain.requests;

import com.gosterici.adesso.employeeservice.domain.EmployeeRole;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateEmployeeRequest {
  private String name;
  private String surname;
  private String email;
  private String phoneNumber;
  private List<EmployeeRole> roles;
}
