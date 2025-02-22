package com.gosterici.adesso.employeeservice.domain.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gosterici.adesso.employeeservice.domain.ProjectState;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateProjectRequest {

    private String title;
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    private ProjectState status;
    private List<UUID> employeeIds;
}
