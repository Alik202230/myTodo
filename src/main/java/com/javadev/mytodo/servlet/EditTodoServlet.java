package com.javadev.mytodo.servlet;

import com.javadev.mytodo.model.Status;
import com.javadev.mytodo.model.Todo;
import com.javadev.mytodo.model.User;
import com.javadev.mytodo.service.TodoService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditTodoServlet extends HttpServlet {

  private final TodoService todoService = new TodoService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    Todo todo = this.todoService.getTodoById(id);
    req.setAttribute("todo", todo);
    req.getRequestDispatcher("/WEB-INF/editTodo.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    String title = req.getParameter("title");
    Status status = Status.valueOf(req.getParameter("status"));

    Todo todo = Todo.builder()
        .id(id)
        .title(title)
        .status(status)
        .build();
    this.todoService.updateTodo(todo);
    resp.sendRedirect("/todos");
  }
}
