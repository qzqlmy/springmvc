<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/10 0010
  Time: 下午 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body >
<span style="font-size:20px; color:#66CD00" ><span >&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已选课程信息</span></span>
<table border="1"width="1300px" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">
    <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
        <td >id</td>
        <td>课程编号</td>
        <td>科目</td>
        <td>上课地点</td>
        <td>上课时间</td>
        <td>课时</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach items="${sessionScope.subjects}" var="subject" varStatus="vs">
        <tr>
            <td style="background: #e5efeb; text-align: center;height: 2.2rem;">${vs.count}</td>
            <td>${subject.subjectid}</td>
            <td>${subject.subject}</td>
            <td>${subject.classplace}</td>
            <td>${subject.classtime}</td>
            <td>${subject.classhour}</td>
            <td><a href="/teacher/queryById/${subject.id}">修改</a></td>
            <td><a href="/teacher/remove/${subject.id}" onclick="return del()">删除</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
