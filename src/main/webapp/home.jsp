<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/21 0021
  Time: 下午 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/stylel.css">
    <title>home</title>
</head>

<body>
<c:if test="${sessionScope.email eq null}" >
    <c:redirect url="login.jsp"/>
</c:if>
<div class="login-form">
<form action="/user/bookcreate" method="post">
    <input type="text" name="title" placeholder="title"><br>
    <input type="text" name="author" placeholder="author"><br>
    <input type="text" name="publish" placeholder="publish"><br>
    <input type="submit" value="create">
</form><br>
    </div>
<table border="1">
    <tr>
        <td>id</td>
        <td>title</td>
        <td>author</td>
        <td>publish</td>
        <td>operate</td>
    </tr>
    <c:forEach items="${sessionScope.books}" var="book" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${book.title}</td>
            <td>${book.publish}</td>
            <td>${book.author}</td>
            <td><a href="/user/queryById/${book.id}">modify</a></td>
            <td><a href="/user/remove/${book.id}" onclick="return del()">remove</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
