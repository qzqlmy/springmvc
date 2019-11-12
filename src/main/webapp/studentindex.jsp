<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.md5.js"></script>
    <script type="text/javascript" src="js/jsencrypt.min.js"></script>
    <script src="js/studentindex.js"></script>

</head>

<body style="background-image:url(images/e33471629415c521f7ab25608d016801.png)">

<img class="bgone" src="images/e33471629415c521f7ab25608d016801.png">
<%--<img class="pic" src="images/e33471629415c521f7ab25608d016801.png">--%>

<div class="table" >
    <div class="wel">管理系统</div>
    <div class="wel1">MOU MOU XI TONG HUO TAI DENG LU</div>
    <form action="/teacher/logins" method="post">
    <div class="user">
        <div id="yonghu" style=""><img src="images/yhm.png"></div>
        <input id="yhm"  type="text"  name="email"  placeholder="用户名"/>
        <%--<input type="text" name="" placeholder="用户名">--%>
    </div>
    <div class="password">
        <div id="yongh"><img src="images/mm.png"></div>
        <%--<input type="password" name="密码" placeholder="*******">--%>
        <input id="password"   type="password"  name="password"  placeholder="password"/>
    </div>
        <div class="password" style="top: 17.8rem;">
            <div id="yongh"><img src="images/mm.png"></div>
            <%--<input type="password" name="密码" placeholder="*******">--%>
            <input id="passwor"   style="width: 200px" placeholder="图形验证码"  name="password"  />
            <div  class="code-box" style="width: 173px;margin-left: 2px"><img style="width:128px;height:32px;margin-left: 1px"/></div>
        </div>
    <input id="yzm" class="btn"  style="top: 23.5rem" name="登录" value="登录">
    </form>
</div>


</body></html>