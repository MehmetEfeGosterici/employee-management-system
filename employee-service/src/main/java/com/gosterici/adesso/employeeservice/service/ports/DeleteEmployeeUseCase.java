package com.gosterici.adesso.employeeservice.service.ports;

import com.gosterici.adesso.employeeservice.domain.Employee;

import java.util.Optional;
import java.util.UUID;

public interface DeleteEmployeeUseCase {

    void deleteEmployee(UUID employeeId);
}
