<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.job4j.servlets.model.User" %>
<%@ page import="ru.job4j.servlets.model.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.10.2018
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>

<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    New login:<br><input type="text" name='login' value="${user.login}"/><br>
    New password:<br><input type="text" name='password' value="${user.password}"><br>
    New email:<br><input type="text" name='email' value="${user.email}"/><br>
    New role:<br><input type="text" name='role' value="${user.role}"><br>
    New address:<br> <input type="text" name='address' value="${user.address}"><br>
    <input type='hidden' name='id' value="${user.id}">
    <input type='submit' value='Update user'>
</form>

</body>
</html>
