<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/7 0007
  Time: 下午 7:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/21 0021
  Time: 下午 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>乌云后台登录</title>
    <!-- Custom Theme files -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="后台登录" />
    <!--Google Fonts-->
    <!--<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    --><!--Google Fonts-->
</head>
<body>
<!--header start here-->
<div class="login-form">

    <h1>管理员登录页面</h1>
    <div class="login-top">
        <form action="/student/login" method="post">
            <div class="login-ic">
                <i ></i>
                <input type="text"  name="email"  placeholder="email"/>
                <div class="clear"> </div>
            </div>
            <div class="login-ic">
                <i class="icon"></i>
                <input type="password"  name="password"  placeholder="password"/>
                <div class="clear"> </div>
            </div>

            <div class="log-bwn">
                <input type="submit"  value="Login" >
            </div>
            ${requestScope.message}
            <a href="/index.jsp" class="log-bwn">sign up</a>
        </form>
    </div>
    <p class="copy">${requestScope.message}</p>

    <p class="copy"></p>
</div>
<!--header start here-->
</body>
</html>
</body>
</html>
