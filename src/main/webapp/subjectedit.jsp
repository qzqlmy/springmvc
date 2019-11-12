<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程修改</title>
</head>
<body>
<form action="/teacher/modify" method="post">
    <input type="text" name="subjectid" placeholder="subjectid" value="${sessionScope.subject.subjectid}"><br>
    <input type="text" name="subject" placeholder="subject" value="${sessionScope.subject.subject}"><br>
    <input type="text" name="classplace" placeholder="classplace" value="${sessionScope.subject.classplace}"><br>
    <input type="text" name="classtime" placeholder="classtime" value="${sessionScope.subject.classtime}"><br>
    <input type="text" name="classhour" placeholder="classhour" value="${sessionScope.subject.classhour}"><br>
    <input type="submit" value="save">
</form><br>
</body>
</html>
