package com.gosterici.adesso.employeeservice.service.ports;

import com.gosterici.adesso.employeeservice.domain.EmployeeRole;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public interface CreateEmployeeUseCase {

    void createEmployee(CreateEmployeeCommand command);

    @Getter
    @Builder
    class CreateEmployeeCommand {
        public String name;
        public String surname;
        public String email;
        public String phoneNumber;
        public List<EmployeeRole> roles;
    }
}
