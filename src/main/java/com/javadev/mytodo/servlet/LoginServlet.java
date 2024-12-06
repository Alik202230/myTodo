package com.javadev.mytodo.servlet;

import com.javadev.mytodo.model.User;
import com.javadev.mytodo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();

    String email = req.getParameter("email");
    String password = req.getParameter("password");

    User user = this.userService.getUserByEmailAndPassword(email, password);
    if (user != null) {
      session.setAttribute("user", user);
      resp.sendRedirect("/todos");
    } else {
      session.setAttribute("msg", "username or password is incorrect");
      resp.sendRedirect("/login");
    }

  }
}
