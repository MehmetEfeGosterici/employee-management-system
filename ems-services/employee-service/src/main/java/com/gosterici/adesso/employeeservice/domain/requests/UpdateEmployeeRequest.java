package com.gosterici.adesso.employeeservice.domain.requests;

import java.util.UUID;
import lombok.Getter;

@Getter
public class UpdateEmployeeRequest {
  private UUID employeeId;
  private String name;
  private String surname;
  private String email;
  private String phoneNumber;
}
