package com.javadev.mytodo.service;

import com.javadev.mytodo.db.SQLConnectionProvider;
import com.javadev.mytodo.model.Status;
import com.javadev.mytodo.model.Todo;
import com.javadev.mytodo.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();
  private final UserService userService = new UserService();

  public void add(Todo todo) {
    String sql = "INSERT INTO todo(title, user_id, created_at, status) VALUES(?,?,?,?)";
    try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, todo.getTitle());
      statement.setInt(2, todo.getUser().getId());
      statement.setString(3, DateUtil.fromDateToSqlDateTimeString(todo.getCreatedAt()));
      statement.setString(4, todo.getStatus().name());
      statement.executeUpdate();
      try (ResultSet resultSet = statement.getGeneratedKeys()) {
        if (resultSet.next()) {
          todo.setId(resultSet.getInt(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Todo> getTodosByUserId(int userId) {
    String query = "SELECT id, title, created_at, status FROM todo WHERE user_id = ?";
    List<Todo> result = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, userId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Todo todo = new Todo(
              resultSet.getInt("id"),
              resultSet.getString("title"),
              DateUtil.fromSqlStringToDateTime(resultSet.getString("created_at")),
              Status.valueOf(resultSet.getString("status"))
          );
          result.add(todo);
        }
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public void deleteTodoById(int todoId, int userId) {
    String query = "DELETE FROM todo WHERE id = ? AND user_id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, todoId);
      statement.setInt(2, userId);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateTodo(Todo todo) {
    String query = "UPDATE todo SET title = ?,  status = ? WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, todo.getTitle());
      statement.setString(2, todo.getStatus().name());
      statement.setInt(3, todo.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Todo getTodoById(int id) {
    String query = "SELECT * FROM todo WHERE id = " + id;
    try (Statement statement = connection.createStatement()) {
      try (ResultSet resultSet = statement.executeQuery(query)) {
        if (resultSet.next()) {
          return Todo.builder()
              .id(resultSet.getInt("id"))
              .title(resultSet.getString("title"))
              .status(Status.valueOf(resultSet.getString("status")))
              .user(this.userService.getUserById(resultSet.getInt("user_id")))
              .build();
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
}
