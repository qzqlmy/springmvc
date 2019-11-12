<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/9 0009
  Time: 下午 3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/teacher/modifystudent" method="post">


    <input type="text" name="studentid" placeholder="studentid" value="${sessionScope.student.studentid}"><br>
    <input type="text" name="name" placeholder="name" value="${sessionScope.student.name}"><br>
    <input type="text" name="classname" placeholder="classname" value="${sessionScope.student.classname}"><br>
    <input type="submit" value="save">
</form><br>
</body>
</html>
