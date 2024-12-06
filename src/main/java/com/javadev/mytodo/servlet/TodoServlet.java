package com.javadev.mytodo.servlet;

import com.javadev.mytodo.model.Todo;
import com.javadev.mytodo.model.User;
import com.javadev.mytodo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/todos")
public class TodoServlet extends HttpServlet {
  private final TodoService todoService = new TodoService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
      List<Todo> allTodos = this.todoService.getTodosByUserId(currentUser.getId());
      req.setAttribute("allTodos", allTodos);
      req.getRequestDispatcher("/WEB-INF/todos.jsp").forward(req, resp);
    } else {
      req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
  }
}