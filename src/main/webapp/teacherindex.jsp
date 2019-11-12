<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="//libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script src="js/teacher.js"></script>
    <!--Google Fonts-->
    <!--<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    --><!--Google Fonts-->
</head>
<body>
<!--header start here-->
<div class="login-form">

    <h1>教师登录页面</h1>
    <div class="login-top">
        <form action="/teacher/login" id="login" method="post">
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
            <div class="log-bwn" id="button">
                <span type="submi">login</span>
            </div>
            <%--<div class="log-bwn">--%>
                <%--<input type="button"  id="button" value="Login" >--%>
            <%--</div>--%>
            ${requestScope.message}
            <a href="/index.jsp" class="log-bwn">sign up</a>
        </form>
    </div>
    <p class="copy">${requestScope.message}</p>

    <p class="copy">© 2016 xxxxxxxxxxx</p>
</div>
<!--header start here-->
</body>
</html>
</body>
</html>
