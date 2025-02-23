package com.gosterici.adesso.employeeservice.service.employee;

import com.gosterici.adesso.employeeservice.controller.resource.EmployeeResource;
import com.gosterici.adesso.employeeservice.domain.Employee;
import com.gosterici.adesso.employeeservice.domain.EmployeeRole;
import com.gosterici.adesso.employeeservice.domain.Role;
import com.gosterici.adesso.employeeservice.repositories.EmployeeRepository;
import com.gosterici.adesso.employeeservice.repositories.RoleRepository;
import com.gosterici.adesso.employeeservice.service.employee.mapper.EmployeeMapper;
import com.gosterici.adesso.employeeservice.service.ports.CreateEmployeeUseCase;
import com.gosterici.adesso.employeeservice.service.ports.DeleteEmployeeUseCase;
import com.gosterici.adesso.employeeservice.service.ports.GetEmployeeQuery;
import com.gosterici.adesso.employeeservice.service.ports.UpdateEmployeeUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService
    implements CreateEmployeeUseCase,
        GetEmployeeQuery,
        DeleteEmployeeUseCase,
        UpdateEmployeeUseCase {

  private final EmployeeRepository employeeRepository;
  private final RoleRepository roleRepository;

  // TODO add specification and Pageable
  @Override
  public Optional<EmployeeResource> getEmployee(UUID employeeId) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    if (optionalEmployee.isPresent()) {
      Employee employee = optionalEmployee.get();
      return Optional.of(EmployeeMapper.INSTANCE.mapEmployeeToResource(employee));
    }
    ;
    return Optional.empty();
  }

  // TODO add specification and Pageable
  public List<Employee> getEmployees(List<UUID> employeeIds) {
    return employeeRepository.findAllById(employeeIds);
  }

  public List<Employee> findAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public void createEmployee(CreateEmployeeCommand command) {
    final List<EmployeeRole> roles = command.getRoles();
    final Employee employee =
        Employee.builder()
            .name(command.getName())
            .surname(command.getSurname())
            .email(command.getEmail())
            .phoneNumber(command.getPhoneNumber())
            .build();
    employeeRepository.save(employee);
    createRolesForEmployee(employee, roles);
  }

  private void createRolesForEmployee(Employee employee, List<EmployeeRole> roles) {
    final Set<Role> roleList =
        roles.stream()
            .map(roleName -> Role.builder().role(roleName).employee(employee).build())
            .collect(Collectors.toSet());
    roleRepository.saveAll(roleList);
    employee.addRoles(roleList);
  }

  public Optional<Employee> updateEmployee(UpdateEmployeeCommand command) {
    final UUID employeeId = command.getEmployeeId();
    Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
    if (optionalEmployee.isPresent()) {
      Employee employee = optionalEmployee.get();
      employee.setName(command.getName());
      employee.setSurname(command.getSurname());
      employee.setEmail(command.getEmail());
      employee.setPhoneNumber(command.getPhoneNumber());
      return Optional.of(employee);
    } else {
      return Optional.empty();
    }
  }

  public void deleteEmployee(UUID employeeId) {
    employeeRepository.deleteById(employeeId);
  }
}
