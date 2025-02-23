package com.gosterici.adesso.employeeservice.service.project;

import com.gosterici.adesso.employeeservice.domain.Employee;
import com.gosterici.adesso.employeeservice.domain.Project;
import com.gosterici.adesso.employeeservice.repositories.EmployeeRepository;
import com.gosterici.adesso.employeeservice.repositories.ProjectsRepository;
import com.gosterici.adesso.employeeservice.service.project.ports.CreateProjectUseCase;
import com.gosterici.adesso.employeeservice.service.project.ports.GetProjectsQuery;
import com.gosterici.adesso.employeeservice.service.project.ports.UpdateProjectUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService
    implements GetProjectsQuery, CreateProjectUseCase, UpdateProjectUseCase {

  private final ProjectsRepository projectsRepository;
  private final EmployeeRepository employeeRepository;

  public List<Project> getProjects() {
    return projectsRepository.findAll();
  }

  public Optional<Project> getProject(UUID projectId) {
    return projectsRepository.findById(projectId);
  }

  public void createProject(CreateProjectCommand command) {
    final List<UUID> employeeIds = command.getEmployeeIds();
    final List<Employee> employeeList = employeeRepository.findAllById(employeeIds);

    Project newProject =
        Project.builder()
            .title(command.getTitle())
            .description(command.getDescription())
            .startDate(command.getStartDate())
            .status(command.getStatus())
            .build();
    projectsRepository.save(newProject);

    employeeList.forEach(
        employee -> {
          employee.addProjects(List.of(newProject));
          newProject.addProjectContributors(List.of(employee));
        });
  }

  public void updateProjectContributors(UpdateProjectContributorsCommand command) {
    final UUID projectId = command.getProjectId();
    final Optional<Project> optionalProject = projectsRepository.findById(projectId);
    if (optionalProject.isPresent()) {
      final Project project = optionalProject.get();
      final List<UUID> employeesToBeAdded = command.getEmployeesToBeAdded();
      final List<UUID> employeesToBeRemoved = command.getEmployeesToBeRemoved();
      addProjectContributors(project, employeesToBeAdded);
      removeProjectContributors(project, employeesToBeRemoved);
    }
  }

  private void addProjectContributors(Project project, List<UUID> employeesToBeAdded) {
    final List<Employee> employees = employeeRepository.findAllById(employeesToBeAdded);
    employees.forEach(
        employee -> {
          employee.addProjects(List.of(project));
          project.addProjectContributors(List.of(employee));
        });
  }

  private void removeProjectContributors(Project project, List<UUID> employeesToBeRemoved) {
    final List<Employee> employees = employeeRepository.findAllById(employeesToBeRemoved);
    employees.forEach(
        employee -> {
          employee.removeProjects(List.of(project));
          project.removeProjectContributors(List.of(employee));
        });
  }
}
