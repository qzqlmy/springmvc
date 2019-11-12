<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/modify" method="post">


    <input type="text" name="email" placeholder="email" value="${sessionScope.teacher.email}"><br>
    <input type="text" name="password" placeholder="password" value="${sessionScope.teacher.password}"><br>
    <input type="submit" value="save">
</form><br>
</body>
</html>
</body>
</html>
