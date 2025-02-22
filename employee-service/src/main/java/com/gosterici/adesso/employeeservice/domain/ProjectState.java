package com.gosterici.adesso.employeeservice.domain;

public enum ProjectState {

    PLANNING,
    IN_DEVELOPMENT,
    COMPLETED,
    CANCELLED,
    ON_HOLD;

    public static ProjectState getFromName(String name){
        for(ProjectState state : ProjectState.values()){
            if(state.name().equals(name)){
                return state;
            }
        }
        return null;
    }
}
