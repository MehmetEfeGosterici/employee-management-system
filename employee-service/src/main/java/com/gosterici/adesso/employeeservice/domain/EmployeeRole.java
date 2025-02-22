package com.gosterici.adesso.employeeservice.domain;

public enum EmployeeRole {

    ADMIN,
    MANAGER,
    SUPERVISOR,
    EMPLOYEE,
    CONTRACTOR,
    INTERN;

    public static EmployeeRole getFromName(String name){
        for(EmployeeRole role : EmployeeRole.values()){
            if(role.name().equals(name)){
                return role;
            }
        }
        return null;
    }
}
