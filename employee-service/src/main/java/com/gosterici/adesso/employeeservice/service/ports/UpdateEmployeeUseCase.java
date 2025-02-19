package com.gosterici.adesso.employeeservice.service.ports;

import com.gosterici.adesso.employeeservice.domain.Employee;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

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
