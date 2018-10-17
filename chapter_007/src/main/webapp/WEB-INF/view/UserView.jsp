<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.10.2018
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
   <h1>Hello, User!</h1>
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
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.address}</td>
                <td>${user.createDate}</td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type='hidden' name='id' value="${user.id}">
                        <input type='submit' value='Edit'>
                    </form>
                </td>
            </tr>
    </table>
    <br>
    <form action="${pageContext.servletContext.contextPath}/logout" method="get">
        <input type='submit' value='Logout'>
    </form>
</head>
<body>

</body>
</html>
