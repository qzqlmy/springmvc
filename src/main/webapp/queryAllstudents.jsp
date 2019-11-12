<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/10 0010
  Time: 下午 2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br>
<span style="font-size:20px; color:#66CD00" ><span >&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生信息</span></span>
<table border="1" width="1300px" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">
    <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
        <td>id</td>
        <td>学号</td>
        <td>姓名</td>
        <td>班级</td>

        <td colspan="2">操作</td>
    </tr>
    <c:forEach items="${sessionScope.students}" var="student" varStatus="vs">
        <tr style="background: #e5efeb; text-align: center;height: 2.2rem;">
            <td>${vs.count}</td>
            <td>${student.studentid}</td>
            <td>${student.name}</td>
            <td>${student.classname}</td>

            <td><a href="/teacher/queryByIdstudent/${student.id}">修改</a></td>
            <td><a href="/teacher/removestudent/${student.id}" onclick="return del()">删除</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
