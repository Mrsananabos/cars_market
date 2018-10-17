<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.10.2018
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign in</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color:red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login:<br><input type="text" name='login'/><br>
    Password:<br><input type="password" name='password'/><br>
    <input type='submit' value='Sign in'>
</form>

</body>
</html>
