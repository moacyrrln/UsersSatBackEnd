// E:\dev\SAT/users\src\main\java\com\sat/users\dto\UserDTO.java
package com.sat.users.dto;

public class UserDTO {
  private Long Id;
  private String name;
  private String email;
  private long cpf;
  private long phone;

  public UserDTO() {}

  public UserDTO(Long id, String name, String email, long cpf, long phone) {
    Id = id;
    this.name = name;
    this.email = email;
    this.cpf = cpf;
    this.phone = phone;
  }

  public void setId(Long id) {
    Id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDTO(String name, String email, long cpf, long phone) {
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
}
