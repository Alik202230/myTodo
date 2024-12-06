<%@ page import="com.javadev.mytodo.model.Todo" %>
<%@ page import="com.javadev.mytodo.model.Status" %>
<%@ page import="com.javadev.mytodo.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: alik
  Date: 12/6/24
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Todo Page</title>
</head>
<body>
<% Todo todo = (Todo) request.getAttribute("todo");%>
<form action="/edit" method="post">
  <input type="hidden" name="id" value="<%=todo.getId()%>"><br>
  Title: <input type="text" name="title" value="<%=todo.getTitle()%>"><br>
  STATUS: <select name="status">
  <%if (todo.getStatus() == Status.NEW) {%>
  <option value="NEW" selected>NEW</option>
  <option value="DONE">DONE</option>
  <%} else {%>
  <option value="MALE">MALE</option>
  <option value="FEMALE" selected>FEMALE</option>
  <%}%>
</select><br>Add
  <input type="submit" value="UPDATE">
</form>
</body>
</html>
