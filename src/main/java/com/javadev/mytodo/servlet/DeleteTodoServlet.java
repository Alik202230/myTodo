package com.javadev.mytodo.servlet;

import com.javadev.mytodo.model.User;
import com.javadev.mytodo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteTodoServlet extends HttpServlet {

  private final TodoService todoService = new TodoService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int todoId = Integer.parseInt(req.getParameter("id"));
    int userId = ((User) req.getSession().getAttribute("user")).getId();
    this.todoService.deleteTodoById(todoId, userId);
    resp.sendRedirect("/todos");
  }
}
