//E:\dev\SAT/users\src\main\java\com\sat/users\model\Users.java
package com.sat.users.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String name;
  private String email;
  private long cpf;
  private long phone;

  public Users() {
  }

  public Users(String name, String email, long cpf, long phone) {
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
  }

  public Users(Long id, String name, String email, long cpf, long phone) {
    this.Id = id;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
  }

  public Long getId() {
    return Id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public long getCpf() {
    return cpf;
  }

  public long getPhone() {
    return phone;
  }

  public void setId(Long id) {
    Id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCpf(long cpf) {
    this.cpf = cpf;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }
}
