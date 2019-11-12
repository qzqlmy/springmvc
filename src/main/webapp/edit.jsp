<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/21 0021
  Time: 下午 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/modify" method="post">
    <input type="text" name="title" placeholder="title" value="${sessionScope.book.id}"><br>
    <input type="text" name="title" placeholder="title" value="${sessionScope.book.title}"><br>
    <input type="text" name="author" placeholder="author" value="${sessionScope.book.author}"><br>
    <input type="text" name="publish" placeholder="publish" value="${sessionScope.book.publish}"><br>
    <input type="submit" value="save">
</form><br>
</body>
</html>
