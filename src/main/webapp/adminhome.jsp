<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/7 0007
  Time: 下午 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/teacher.css">
</head>
<body>
<a href="/index.jsp" class="log-bwn">返回首页面</a>
<div class="login-form">
<form action="/admin/teachercreate" method="post">
    <div class="login-ic">
    <input type="text" name="email" placeholder="email"><br>
    <input type="text" name="password" placeholder="password"><br>
    <input type="submit" value="添加教师信息">
    </div>
</form>
<form action="/admin/createstudent" method="post">
    <div class="login-ic">
    <input type="text" name="email" placeholder="学号"><br>
    <input type="text" name="password" placeholder="密码"><br>
    <input type="submit" value="添加学生信息">
        </div>
</form>
</div>
<table border="1" width="1300px" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;" >
    <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
        <td>id</td>

        <td>email</td>
        <td>password</td>
        <td colspan="2">operate</td>
    </tr>
    <c:forEach items="${sessionScope.teachers}" var="teacher" varStatus="vs">
        <tr style="background: #e5efeb; text-align: center;height: 2.2rem;">
            <td>${vs.count}</td>

            <td>${teacher.email}</td>
            <td>${teacher.password}</td>
            <td><a href="/admin/queryById/${teacher.id}">修改</a></td>
            <td><a href="/admin/remove/${teacher.id}" onclick="return del()">删除</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
