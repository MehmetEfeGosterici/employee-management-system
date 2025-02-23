package com.gosterici.adesso.employeeservice.service.ports;

import com.gosterici.adesso.employeeservice.domain.Employee;
import java.util.Optional;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

public interface UpdateEmployeeUseCase {

  Optional<Employee> updateEmployee(UpdateEmployeeCommand command);

  @Getter
  @Builder
  class UpdateEmployeeCommand {
    private UUID employeeId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
  }
}
