package com.javadev.mytodo.service;

import com.javadev.mytodo.db.SQLConnectionProvider;
import com.javadev.mytodo.model.User;

import java.sql.*;

public class UserService {

  private final Connection connection = SQLConnectionProvider.getInstance().getConnection();

  public void add(User user) {
    String query = "INSERT INTO user(name, surname, email, password) VALUES(?, ?, ?, ?)";
    try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, user.getName());
      statement.setString(2, user.getSurname());
      statement.setString(3, user.getEmail());
      statement.setString(4, user.getPassword());
      statement.executeUpdate();
      try(ResultSet resultSet = statement.getGeneratedKeys()) {
        if (resultSet.next()) {
          user.setId(resultSet.getInt(1));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public User getUserByEmailAndPassword(String email, String password) {
    String query = "SELECT * FROM user WHERE email = ? AND password = ?";
    try(PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, email);
      statement.setString(2, password);
      try(ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return new User(
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getString("surname"),
              resultSet.getString("email"),
              resultSet.getString("password")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public User getUserByEmail(String email) {
    String query = "SELECT * FROM user WHERE email = ?";
    try(PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, email);
      try(ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return new User(
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getString("surname"),
              resultSet.getString("email"),
              resultSet.getString("password")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public User getUserById(int id) {
    String query = "SELECT * FROM user WHERE id = ?";
    try(PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);
      try(ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return new User(
              resultSet.getInt("id"),
              resultSet.getString("name"),
              resultSet.getString("surname"),
              resultSet.getString("email"),
              resultSet.getString("password")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

}
