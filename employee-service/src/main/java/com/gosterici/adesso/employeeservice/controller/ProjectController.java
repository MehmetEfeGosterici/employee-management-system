package com.gosterici.adesso.employeeservice.controller;

import com.gosterici.adesso.employeeservice.domain.requests.CreateProjectRequest;
import com.gosterici.adesso.employeeservice.domain.requests.UpdateProjectContributorsRequest;
import com.gosterici.adesso.employeeservice.service.project.ports.CreateProjectUseCase;
import com.gosterici.adesso.employeeservice.service.project.ports.GetProjectsQuery;
import com.gosterici.adesso.employeeservice.service.project.ports.UpdateProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final GetProjectsQuery getProjectsQuery;
    private final CreateProjectUseCase createProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;

    @GetMapping
    public void getProjects(){

    }

    @GetMapping("/{projectId}")
    public void getProject(@PathVariable String projectId){

    }

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody CreateProjectRequest createProjectRequest){
        CreateProjectUseCase.CreateProjectCommand command = CreateProjectUseCase.CreateProjectCommand.builder()
                .title(createProjectRequest.getTitle())
                .description(createProjectRequest.getDescription())
                .startDate(createProjectRequest.getStartDate())
                .status(createProjectRequest.getStatus())
                .employeeIds(createProjectRequest.getEmployeeIds())
                .build();
        createProjectUseCase.createProject(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/projectContributors/{projectId}")
    public ResponseEntity<Void> updateProjectContributors(@PathVariable UUID projectId, @RequestBody UpdateProjectContributorsRequest updateProjectContributorsRequest){
        UpdateProjectUseCase.UpdateProjectContributorsCommand updateProjectContributorsCommand = UpdateProjectUseCase.UpdateProjectContributorsCommand.builder()
                .projectId(projectId)
                .employeesToBeAdded(updateProjectContributorsRequest.getEmployeesToBeAdded())
                .employeesToBeRemoved(updateProjectContributorsRequest.getEmployeesToBeRemoved())
                .build();
        updateProjectUseCase.updateProjectContributors(updateProjectContributorsCommand);
        return ResponseEntity.ok().build();
    }
}
