package com.javadev.mytodo.servlet;

import com.javadev.mytodo.model.Status;
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
import java.util.Date;

@WebServlet("/add-todo")
public class AddTodoServlet extends HttpServlet {

  private final TodoService todoService = new TodoService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    String title = req.getParameter("title");
    Todo todo = new Todo(title, user, new Date(), Status.NEW);
    this.todoService.add(todo);
    resp.sendRedirect("/todos");
  }
}
