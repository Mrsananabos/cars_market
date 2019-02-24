<%@ page import="ru.job4j.servlets.model.ValidateService" %>
<%@ page import="ru.job4j.servlets.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Login:<br><input type="text" name='login'/><br>
    Password:<br><input type="text" name='password'/><br>
    Email:<br><input type="text" name='email'/><br>
    Role:
    <select name="role">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select><br>
    Address:<br><input type="text" name='address'/><br>
    <input type='submit' value='Add'>
</form>

<table style="border: 1px solid cornflowerblue;" border="1" cellspacing="3" cellpadding="3" width="56%">
    <tr>
        <th width="8%">id</th>
        <th width="8%">login</th>
        <th width="8%">role</th>
        <th width="8%">email</th>
        <th width="8%">password</th>
        <th width="8%">address</th>
        <th width="8%">date</th>
        <th width="8%">action</th>
    </tr>
    <br>
    <c:forEach var="user" items="${service.findAll()}" >
    <tr>
        <td><с:out value="${user.id}"/></td>
        <td><с:out value="${user.login}"/></td>
        <td><с:out value="${user.role}"/></td>
        <td><с:out value="${user.email}"/></td>
        <td><с:out value="${user.password}"/></td>
        <td><с:out value="${user.address}"/></td>
        <td><с:out value="${user.createDate}"/></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type='hidden' name='id' value="${user.id}">
                <input type='submit' value='Edit'>
            </form>
            <form action="${pageContext.servletContext.contextPath}/" method='post'>
                <input type='hidden' name='id' value="${user.id}">
                <input type='submit' value='Delete'>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.servletContext.contextPath}/logout" method="get">
    <input type='submit' value='Logout'>
</form>

</body>
</html>
