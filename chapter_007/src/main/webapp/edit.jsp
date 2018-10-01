<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.ValidateService" %><%--
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
<form action="<%=request.getContextPath()%>/edit" method="post">
    <%String id = request.getParameter("id");%>
    <%User currentUser = ValidateService.getInstance().findById(Integer.valueOf(id));%>
    New login:<br><input type="text" name='login' value="<%=currentUser.getLogin()%>"/><br>
    New password:<br><input type="text" name='password' value="<%=currentUser.getPassword()%>"><br>
    New email:<br><input type="text" name='email' value="<%=currentUser.getEmail()%>"/><br>
    New role:<br><input type="text" name='role' value="<%=currentUser.getRole()%>"><br>
    New address:<br> <input type="text" name='address' value="<%=currentUser.getAddress()%>"/><br>
    <input type='hidden' name='id' value="<%=id%>">
    <input type='submit' value='Update user'>

</form>
</body>
</html>
