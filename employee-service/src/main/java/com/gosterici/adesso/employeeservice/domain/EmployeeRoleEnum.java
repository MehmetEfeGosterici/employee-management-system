package com.gosterici.adesso.employeeservice.domain;

public enum EmployeeRoleEnum {

    ADMIN,
    MANAGER,
    SUPERVISOR,
    EMPLOYEE,
    CONTRACTOR,
    INTERN;

    public static EmployeeRoleEnum getFromName(String name){
        for(EmployeeRoleEnum role : EmployeeRoleEnum.values()){
            if(role.name().equals(name)){
                return role;
            }
        }
        return null;
    }
}
