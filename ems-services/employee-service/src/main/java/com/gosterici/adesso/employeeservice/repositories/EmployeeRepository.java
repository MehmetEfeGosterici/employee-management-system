package com.gosterici.adesso.employeeservice.repositories;

import com.gosterici.adesso.employeeservice.domain.Employee;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {}
