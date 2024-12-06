package com.javadev.mytodo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

  private int id;
  private String name;
  private String surname;
  private String email;
  private String password;

  public User(int id, String name, String surname, String email, String password) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
  }

  public User(String name, String surname, String email, String password) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
  }

}
