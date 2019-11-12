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
<body>

<span style="font-size:20px; color:#66CD00" ><span >&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生已选课程明细</span></span>

<table border="1" width="1300px" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">
    <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
        <td>id</td>
        <td>学号</td>
        <td>科目</td>
        <td>姓名</td>
        <td>班级</td>

    </tr>
    <c:forEach items="${sessionScope.subjects}" var="subject" varStatus="vs">
        <tr style="background: #e5efeb; text-align: center;height: 2.2rem;">
            <td>${vs.count}</td>
            <td>${subject.subjectid}</td>
            <td>${subject.subject}</td>
            <td>${subject.name}</td>
            <td>${subject.classname}</td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
