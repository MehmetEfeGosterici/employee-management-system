package com.gosterici.adesso.employeeservice.service.employee.mapper;

import com.gosterici.adesso.employeeservice.controller.resource.EmployeeResource;
import com.gosterici.adesso.employeeservice.domain.Address;
import com.gosterici.adesso.employeeservice.domain.Employee;
import com.gosterici.adesso.employeeservice.domain.Project;
import com.gosterici.adesso.employeeservice.domain.Role;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Mapping(
      target = "employeeRoles",
      expression = "java(mapRoleToEmployeeRole(employee.getRoles()))")
  @Mapping(
      target = "employeeAddresses",
      expression = "java(mapAddressToEmployeeAddress(employee.getAddresses()))")
  @Mapping(
      target = "employeeProjects",
      expression = "java(mapProjectToEmployeeProject(employee.getProjects()))")
  EmployeeResource mapEmployeeToResource(Employee employee);

  default Set<EmployeeResource.EmployeeRole> mapRoleToEmployeeRole(Set<Role> roles) {
    final Set<EmployeeResource.EmployeeRole> employeeRoles = new HashSet<>();
    for (Role role : roles) {
      EmployeeResource.EmployeeRole employeeRole = new EmployeeResource.EmployeeRole();
      employeeRole.setRoleName(role.getRole().name());
      employeeRole.setActive(role.isActive());
      employeeRoles.add(employeeRole);
    }
    return employeeRoles;
  }

  default Set<EmployeeResource.EmployeeAddress> mapAddressToEmployeeAddress(
      Set<Address> addresses) {
    final Set<EmployeeResource.EmployeeAddress> employeeAddresses = new HashSet<>();
    for (Address address : addresses) {
      EmployeeResource.EmployeeAddress employeeAddress = new EmployeeResource.EmployeeAddress();
      employeeAddress.setStreet(address.getStreet());
      employeeAddress.setCity(address.getCity());
      employeeAddress.setCountry(address.getCountry());
      employeeAddresses.add(employeeAddress);
    }
    return employeeAddresses;
  }

  default List<EmployeeResource.EmployeeProject> mapProjectToEmployeeProject(
      List<Project> projects) {
    final List<EmployeeResource.EmployeeProject> employeeProjects = new ArrayList<>();
    for (Project project : projects) {
      EmployeeResource.EmployeeProject employeeProject = new EmployeeResource.EmployeeProject();
      employeeProject.setTitle(project.getTitle());
      employeeProject.setDescription(project.getDescription());
      employeeProject.setStatus(project.getStatus().name());
      employeeProjects.add(employeeProject);
    }
    return employeeProjects;
  }
}
