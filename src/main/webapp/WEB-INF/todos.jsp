<%@ page import="com.javadev.mytodo.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.javadev.mytodo.model.Todo" %>
<%@ page import="com.javadev.mytodo.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Todos Page</title>
  <!-- Font Awesome -->
  <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      rel="stylesheet"
  />
  <!-- Google Fonts -->
  <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
      rel="stylesheet"
  />
  <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
      rel="stylesheet"
  />
  <!-- Google Fonts -->
  <link
      href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
      rel="stylesheet"
  />
  <!-- MDB -->
  <link
      href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/8.1.0/mdb.min.css"
      rel="stylesheet"
  />
  <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/8.1.0/mdb.umd.min.js"
  ></script>
</head>
<body>
<%
  //not recommended
  @SuppressWarnings("unchecked")
  List<Todo> allTodos = (List<Todo>) request.getAttribute("allTodos");
  User user = (User) session.getAttribute("user");
%>

<section class="vh-100" style="background-color: #eee;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-lg-9 col-xl-7">
        <div class="card rounded-3">
          <div class="card-body p-4">

            <h4 class="text-center my-3 pb-3">Welcome <%=user.getName() + " " + user.getSurname()%></h4>

            <form class="row row-cols-lg-auto g-3 justify-content-center align-items-center mb-4 pb-2" action="/add-todo" method="post">
              <div class="col-12">
                <div data-mdb-input-init class="form-outline">
                  <input type="text" id="form1" class="form-control" name="title"/>
                  <label class="form-label" for="form1">Enter a task here</label>
                </div>
              </div>

              <div class="col-12">
                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary">Add</button>
              </div>
            </form>

            <table class="table mb-4">
              <thead>
              <tr>
                <th scope="col">No.</th>
                <th scope="col">Todo item</th>
                <th scope="col">Status</th>
                <th scope="col">Create At</th>
                <th scope="col">Actions</th>
              </tr>
              </thead>
              <tbody>
              <% for (Todo todo : allTodos) { %>
              <tr>
                <th scope="row"><%=todo.getId()%></th>
                <td><%=todo.getTitle()%></td>
                <td><%=todo.getStatus()%></td>
                <td><%=DateUtil.fromDateToWebString(todo.getCreatedAt())%></td>
                <td>
                  <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger">
                    <a href="/delete?id=<%=todo.getId()%>" style="color: #eee;">Delete</a>
                  </button>
                  <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-success ms-1">
                    <a href="/edit?id=<%=todo.getId()%>" style="color: #eee;">Edit</a>
                  </button>
                </td>
              </tr>
              <%}%>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>
