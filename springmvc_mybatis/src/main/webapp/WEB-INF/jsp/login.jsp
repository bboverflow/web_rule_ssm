<%--
  Created by IntelliJ IDEA.
  User: Rayman
  Date: 2017/3/2
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login.action" method="post">
    用户密码<input type="text" name="username" />
    用户密码<input type="text" name="password" />
    <input type="submit" value="登录">
</form>
</body>
</html>
