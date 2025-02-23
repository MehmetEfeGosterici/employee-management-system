package com.gosterici.adesso.employeeservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Address extends BaseEntity {

  private String street;
  private String city;
  private String country;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;
}
