package com.gosterici.adesso.employeeservice.service.ports;

import com.gosterici.adesso.employeeservice.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetEmployeeQuery {

    Optional<Employee> getEmployee(UUID employeeId);

    List<Employee> getEmployees(List<UUID> employeeIds);

    List<Employee> findAllEmployees();
}
