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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    String surname = req.getParameter("surname");
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    HttpSession session = req.getSession();
    User userEmail = this.userService.getUserByEmail(email);

    if (userEmail != null) {
      System.out.println("user already exist");
      //req.setAttribute("msg", "user already exist");
      //req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    } else {
      User user = new User(name, surname, email, password);
      this.userService.add(user);
      session.setAttribute("msg", "user registered successfully");
    }
    resp.sendRedirect("/register");
  }
}
