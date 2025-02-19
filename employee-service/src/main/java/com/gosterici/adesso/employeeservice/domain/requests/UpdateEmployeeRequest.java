package com.gosterici.adesso.employeeservice.domain.requests;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateEmployeeRequest {
    private UUID employeeId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
