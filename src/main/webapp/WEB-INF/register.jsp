<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register Page</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <!--Fontawesome CDN-->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">

  <link rel="stylesheet" href="../css.style.css">
</head>
<body>
<form action="/register" method="post">
  Name:<input type="text" name="name"><br>
  Surname:<input type="text" name="surname"><br>
  Email:<input type="text" name="email"><br>
  Password:<input type="password" name="password"><br>
  <button type="submit">Click</button>
</form>
</body>
</html>
