<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page import="ru.job4j.servlets.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/create" method="post">
    Login:<br><input type="text" name='login'/><br>
    Password:<br><input type="text" name='password'/><br>
    Email:<br><input type="text" name='email'/><br>
    Role:<br><input type="text" name='role'/><br>
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
    <% for (User user : (ValidateService.getInstance().findAll())) {%>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getRole()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getPassword()%>
        </td>
        <td><%=user.getAddress()%>
        </td>
        <td><%=user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type='hidden' name='id' value="<%=user.getId()%>">
                <input type='submit' value='Edit'>
            </form>
            <form action="<%=request.getContextPath()%>/list" method='post'>
                <input type='hidden' name='id' value="<%=user.getId()%>">
                <input type='submit' value='Delete'>
            </form>
        </td>
    </tr>
    <% } %>
</table>


</body>
</html>
