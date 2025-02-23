package com.gosterici.adesso.employeeservice.service.ports;

import java.util.UUID;

public interface DeleteEmployeeUseCase {

  void deleteEmployee(UUID employeeId);
}
