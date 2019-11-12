<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body bgcolor="#6C7B8B">
<span style="font-size:20px; color:#66CD00" ><span >&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    已选课程信息</span></span>
<p style="color: #2e8d">欢迎你：${sessionScope.studentid}</p>
<table border="1"width="1300px" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">
    <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
        <td>id</td>
        <td>学号</td>
        <td>姓名</td>
        <td>科目</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${sessionScope.students}" var="xuanke" varStatus="vs">
        <tr style="background: #e5efeb; text-align: center;height: 2.2rem;">
            <td>${vs.count}</td>
            <td>${xuanke.studentid}</td>
            <td>${xuanke.name}</td>
            <td>${xuanke.subject}</td>
            <td><a href="/xuanke/remove/${xuanke.id}" onclick="return del()">删除</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
