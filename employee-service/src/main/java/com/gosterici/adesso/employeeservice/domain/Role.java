package com.gosterici.adesso.employeeservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;
    @Builder.Default
    private boolean isActive = true;

    @ManyToOne
    @JsonBackReference
    private Employee employee;
}
