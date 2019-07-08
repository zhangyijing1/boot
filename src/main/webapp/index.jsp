<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/1
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/users" method="post">
  用户名：  <input type="text" name="userName"/>
   密码： <input type="password" name="userPswd">
    <input type="submit" value="确定" >
    <a href="/user/zc">注册</a>
</form>
</body>
</html>
