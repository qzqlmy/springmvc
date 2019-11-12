<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/teacher.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/ajaxfileuploa.js"></script>
    <script src="js/jquery.tips.js"></script>
</head>
<body bgcolor="#FFFFFF">
<a href="/index.jsp" class="log-bwn">返回首页面</a>
<div>
    邮箱: <input id="EMAIL" width="5px;!important"><br>
    标题: <input id="TITLE" width="5px;!important"><p>
    邮件内容:<br>
    <textarea id="CONTENT" style="width:300px;height:100px;"></textarea><br>
    添加附件：<td><input id="upfil" type="file" name="upfile" /></td><br>
    <a id="send" class="btn btn-mini btn-primary" onclick="sendEm();">发送</a>
</div>
    

   
<div class="login-form">
    <form action="/teacher/subjectcreate" method="post">
        <div class="login-ic">
            <input type="text" name="subjectid" placeholder="subjectid"><br>
            <input type="text" name="subject" placeholder="subject"><br>
            <input type="text" name="classplace" placeholder="Classplace"><br>
            <input type="text" name="classtime" placeholder="Classtime"><br>
            <input type="text" name="classhour" placeholder="Classhour"><br>
            <p></p>
            <input type="submit" class="snnnn" value="添加课程信息">
        </div>
    </form>
    <td><input id="upfile" type="file" name="upfile" accept=".xls,.xlsx"/></td>
    <td><input id="btn" name="btn" type="button" class="scbtn" value="文件上传"/></td>
    <form action="/teacher/student" method="post">
        <div class="login-ic">
            <input type="text" name="studentid" placeholder="studentid"><br>
            <input type="text" name="name" placeholder="name"><br>
            <input type="text" name="classname" placeholder="classname"><br>
            <input type="submit" class="snnnn" value="添加学生">
        </div>
    </form>
</div>
<br>
<p></p>
<a href="/teacher/queryAll/">查看课程信息</a>
<a href="/teacher/queryAllstudents/">查看学生信息</a>
<a href="/teacher/queryAllss/">查看学生选课信息</a>

<script type="text/javascript">//JS校验form表单信息
function checkData() {
    var fileDir = $("#upfile").val();
    var suffix = fileDir.substr(fileDir.lastIndexOf("."));
    if ("" == fileDir) {
        alert("选择需要导入的Excel文件！");
        return false;
    }
    if (".xls" != suffix && ".xlsx" != suffix) {
        alert("选择Excel格式的文件导入！");
        return false;
    }
    return true;
}

//ajax提交excel
$("#btn").click(function () {
    if (checkData()) {
        $.ajaxFileUpload({
            url: "/teacher/ajaxUpload.do",
            type: "POST",
            dataType: "text",
            fileElementId: "upfile",
            success: function (data) {
                alert(data)
                console.log(data);
                $("#upfile").val("");
            },
            error: function (erro) {
                console.log(erro);
            }
        });
    }
});

//发送
function sendEm() {

    if ($("#EMAIL").val() == "") {
        $("#EMAIL").tips({
            side: 3,
            msg: '请输入邮箱',
            bg: '#AE81FF',
            time: 2
        });
        $("#EMAIL").focus();
        return false;
    }
    if ($("#TITLE").val() == "") {
        $("#TITLE").tips({
            side: 3,
            msg: '请输入标题',
            bg: '#AE81FF',
            time: 2
        });
        $("#TITLE").focus();
        return false;
    }
    if ($("#CONTENT").val() == "") {

        $("#CONTENT").tips({
            side: 1,
            msg: '请输入内容',
            bg: '#AE81FF',
            time: 3
        });
        return false;
    }

    var EMAIL = $("#EMAIL").val();
    var TYPE = $("#TYPE").val();
    var TITLE = $("#TITLE").val();
    var CONTENT = $("#CONTENT").val();


    $.ajaxFileUpload({
        type: "POST",
        url: '/teacher/sendEmail.do?tm=' + new Date().getTime()+'?EMAIL='+EMAIL+'?TITLE='+TITLE+'?CONTENT='+CONTENT,
        data: {EMAIL: EMAIL, TITLE: TITLE, CONTENT: CONTENT},
        dataType: 'json',
        //beforeSend: validateData,
        cache: false,
        fileElementId: "upfil",
        success: function (data) {
            if(data.result=="ok"){
                $("#upfil").val("");
                  alert("邮件发送成功！");
            }else{
                alert("邮件发送失败！")
            }

        }
    });

}
</script>
</body>
</html>
