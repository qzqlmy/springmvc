<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/14 0014
  Time: 上午 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("input").blur(function(){
                var ele=$(this);
                var getValue=ele.val();//获取创建用户时输入的文本信息
                if($(this).is("#cardId")){
                    var reg=/^\d{18,18}$/;
                    if(reg.test(getValue)==false){
                        flag=false;
                        ele.attr("style","color:red;border:1px solid red;");
                        ele.next().attr("style","color:red;").html("&nbsp;&nbsp;账号输入有误！");
                    }else{
                        ele.attr("style","color:green;border:1px solid green");
                        ele.next().attr("style","color:green;").html("&nbsp;&nbsp;账号输入正确！");
                    }
                }else if($(this).is("#password")){
                    var reg=/^\w{6,}$/;
                    if(reg.test(getValue)==false){
                        ele.attr("style","color:red;border:1px solid red;");
                        ele.next().attr("style","color:red;").html("&nbsp;&nbsp;密码输入有误！");
                    }else{
                        ele.attr("style","color:green;border:1px solid green");
                        ele.next().attr("style","color:green;").html("&nbsp;&nbsp;密码输入正确！");    }
                }

            });
            $("form").submit(function(){
                alert(1);
                var result=false;
                var cardId=$("#cardId");
                var password=$("#password");
                $.ajax({
                    async:false,
                    url:"/users/login",
                    type:"post",
                    data:{"cardId":cardId,"password":password},
                    dataType:"json",
                    success:function(data,xstatus,xhr){
                        if(data==1){
                            alert("该账号不存在，请先注册！")
                        }else if(data==2){
                            alert("登录失败，账号密码不匹配！")
                        }else if(data==3){
                            alert("登录失败，该账号已冻结！")
                        }else if(data==0){
                            result=true;
                        }
                    },
                    error:function(){
                        alert(4);
                    }
                });
                return result;
            });
        });
    </script>
</head>
<body>
<body>
<sp:form modelAttribute="users" action="/user/login" method="POST">
    <div align="center">
        <h2>房产信息查询系统</h2>
        <table>
            <tr><td>请输入身份证号</td><td><sp:input path="cardId" /><span/></td></tr>
            <tr><td>请输入密码</td><td><sp:input path="password" /><span/></td></tr>
        </table>
        <tr><td><input type="submit" value="登录" /></td><td>
            <a href="user/create">注册</a></td></tr>
    </div>
</sp:form>
</body>
</body>
</html>
