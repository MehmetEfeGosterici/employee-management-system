package com.gosterici.adesso.employeeservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Builder.Default
  @OneToMany(mappedBy = "employee", orphanRemoval = true)
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "employee")
  private Set<Address> addresses;

  @ManyToMany
  @JoinTable(
      name = "employee_project",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id"))
  private List<Project> projects;

  public void addRoles(Set<Role> roles) {
    this.roles.addAll(roles);
  }

  public void addProjects(List<Project> projects) {
    this.projects.addAll(projects);
  }

  public void removeProjects(List<Project> projects) {
    this.projects.removeAll(projects);
  }
}
