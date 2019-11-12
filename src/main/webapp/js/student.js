
jQuery(document).ready(function() {
    $("#studentid").focus(function () {
        $("#aaa").text("  ")
    })
    $("#name").focus(function () {
        $("#aaa").text("  ")
    })
    $("#subject").mouseover(function () {
        $("#aaa").text("  ")
    })
    $(".login-ic").focus(function () {
        $("#aaa").text("  ")
    })
    $("#button").click(function () {
        $('#login').submit();
    })


    $(".snnnn").click(function () {
        var studentid=$("#studentid").val()
        var name=$("#name").val()
        var subject=$("#subject").val();
        if(studentid==""){
            $("#studentid").after('<span id="aaa"style="color: #8B1C62">请输入你的学号！</span>')
            return;
        }
        if(name==""){
            $("#name").after('<span id="aaa"style="color: #8B1C62">请输入你的姓名！</span>')
            return;
        }
        if(subject==""){
            $("#subject").after('<span id="aaa"style="color: #8B1C62">请输入你的课程编号！</span>')
            return;
        }
        $.ajax({
            url:"/xuanke/subjectid",
            type:"post",
            data:{"studentid":studentid,"subject":subject,"name":name},
            success:function(data){
                if(data=='true'){
                    $("#subject").after('<span id="aaa"style="color: #8B1C62">你已选过该课程！</span>')
                    return;
                }else{
                    $('#myForm').submit();
                }

            },
            error:function(data){
                alert(data)
                window.location.href="student.jsp";
            }
        });


    })

});