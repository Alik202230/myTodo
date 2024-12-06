package com.javadev.mytodo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
  private int id;
  private String title;
  private User user;
  private Date createdAt;
  private Date finishedAt;
  private Status status;

  public Todo(String title, User user, Date createdAt, Status status) {
    this.title = title;
    this.user = user;
    this.createdAt = createdAt;
    this.status = status;
  }

  public Todo(int id, String title, Date createdAt, Status status) {
    this.id = id;
    this.title = title;
    this.createdAt = createdAt;
    this.status = status;
  }

}
