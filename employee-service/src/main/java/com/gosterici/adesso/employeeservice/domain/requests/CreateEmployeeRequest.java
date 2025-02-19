package com.gosterici.adesso.employeeservice.domain.requests;

import com.gosterici.adesso.employeeservice.domain.EmployeeRoleEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateEmployeeRequest {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private List<EmployeeRoleEnum> roles;
}
