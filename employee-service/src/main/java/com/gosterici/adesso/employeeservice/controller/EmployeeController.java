package com.gosterici.adesso.employeeservice.controller;

import com.gosterici.adesso.employeeservice.controller.resource.EmployeeResource;
import com.gosterici.adesso.employeeservice.domain.Employee;
import com.gosterici.adesso.employeeservice.domain.requests.CreateEmployeeRequest;
import com.gosterici.adesso.employeeservice.domain.requests.UpdateEmployeeRequest;
import com.gosterici.adesso.employeeservice.service.ports.CreateEmployeeUseCase;
import com.gosterici.adesso.employeeservice.service.ports.DeleteEmployeeUseCase;
import com.gosterici.adesso.employeeservice.service.ports.GetEmployeeQuery;
import com.gosterici.adesso.employeeservice.service.ports.UpdateEmployeeUseCase;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
class EmployeeController {

  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final GetEmployeeQuery getEmployeeQuery;
  private final DeleteEmployeeUseCase deleteEmployeeUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;

  @GetMapping(path = "/{employeeId}")
  public ResponseEntity<EmployeeResource> getEmployee(@PathVariable UUID employeeId) {
    Optional<EmployeeResource> optEmployeeResource = getEmployeeQuery.getEmployee(employeeId);
    if (optEmployeeResource.isPresent()) {
      EmployeeResource employeeResource = optEmployeeResource.get();
      return ResponseEntity.ok(employeeResource);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getEmployees(@RequestBody List<UUID> employeeIds) {
    List<Employee> employeeList = getEmployeeQuery.getEmployees(employeeIds);
    return ResponseEntity.ok(employeeList);
  }

  @PostMapping
  public ResponseEntity<Void> createEmployee(@RequestBody CreateEmployeeRequest request) {
    CreateEmployeeUseCase.CreateEmployeeCommand command =
        CreateEmployeeUseCase.CreateEmployeeCommand.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .email(request.getEmail())
            .phoneNumber(request.getPhoneNumber())
            .roles(request.getRoles())
            .build();
    createEmployeeUseCase.createEmployee(command);
    return ResponseEntity.ok().build();
  }

  // TODO this endpoint should be secured by admin role
  @PutMapping
  public ResponseEntity<Void> updateEmployee(@RequestBody UpdateEmployeeRequest request) {
    UpdateEmployeeUseCase.UpdateEmployeeCommand command =
        UpdateEmployeeUseCase.UpdateEmployeeCommand.builder()
            .employeeId(request.getEmployeeId())
            .name(request.getName())
            .surname(request.getSurname())
            .email(request.getEmail())
            .phoneNumber(request.getPhoneNumber())
            .build();
    Optional<Employee> optionalEmployee = updateEmployeeUseCase.updateEmployee(command);
    if (optionalEmployee.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }

  @DeleteMapping(path = "/{employeeId}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
    deleteEmployeeUseCase.deleteEmployee(employeeId);
    return ResponseEntity.ok().build();
  }
}
