
jQuery(document).ready(function() {
    getValidCode();
$("#yhm").focus(function () {
    $("#aaa").text("  ")
})
    $("#password").focus(function () {
        $("#aaa").text("  ")
    })
    $("#yhm").click(function () {
        var yhm=$("#yhm").val()
        if(yhm=='你的用户名或密码错误！'||yhm=='请输入你的用户名'||yhm=='请输入你的密码'){
            $("#yhm").val('');
        }
    })
    $(".code-box").on("click", function() {
        getValidCode();
    });
    function getValidCode(){
        $.ajax({
            url:"/teacher/getValidCode",
            type: 'post',
            dataType: "json",
            success: function(result) {

                var data=result.codee;
                var code=result.code;
               

                if(code=='0000'){

                    $('.code-box img').attr("src",data);
                }else{
                    // $("#yhm").after('<span id="aaa"style="color: #8B1C62">你的用户名或密码错误！</span>')
                    alert("验证码获取失败！");


                }
            },
            error: function (error) {
                alert('验证码异常!' + error);
            }
        });
        // $.ajax({
        //     url: "/teacher/vCode",
        //     type: 'post',
        //     dataType: "json",
        //     success: function(result) {
        //         if(result.code="0000"){
        //             var data=result.data;
        //             $('.code-box img').attr("src",data.code);
        //             sessionStorage.setItem("basePath",data.base);
        //         }else{
        //             alert(result.code+":"+result.message);
        //         }
        //     },
        //     error: function (error) {
        //         alert('验证码异常!' + error);
        //     }
        // });


    }
    $("#yzm").click(function () {
        var je=new JSEncrypt();
        je.setPublicKey("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKRWOPX2+/OGHDiXetm6ss+h0GWTjSImZXPsQQF4Lwa\ne1PQWwShSkiifcxvBT0E7QydiKoTdBoQMHbu4LJIzG0CAwEAAQ==");
        var yhm=$("#yhm").val()
        var password=$("#password").val()
            var result=false;
            var cardId=je.encrypt($("#yhm").val());
            var password=je.encrypt($("#password").val());
            if(cardId==""){
                $("#yhm").val('请输入你的用户名');
                return;
            }
            if(password==""){
                alert('请输入你的密码');
                return;
             }
        var vCd = $.trim($("#passwor").val());
        if(!/^\w{4}$/.test(vCd)){
            alert("输入正确的验证码！");
            return false;
        }

            $.ajax({
                url:"/teacher/ajaxlogin",
                type:"post",
                data:{"cardId":cardId,"password":password,"vCd":vCd},
                success:function(data){
                    // var obj = new Function("return" + teacher)();//转换后的JSON对象
                    //  var code = obj.email;//服务端号
                    if(data=='true'){
                        localStorage.setItem('ID', yhm);
                        window.location.href="/layuiording/index.html";
                    }else if(data=='false3'){
                        // $("#yhm").after('<span id="aaa"style="color: #8B1C62">你的用户名或密码错误！</span>')
                        alert('你的用户名或密码错误！');
                        getValidCode();
                    }else if(data=='false2'){
                        // $("#yhm").after('<span id="aaa"style="color: #8B1C62">你的用户名或密码错误！</span>')
                        alert('你的验证码输入错误！');
                        getValidCode();
                    }else if(data=='false1'){
                        // $("#yhm").after('<span id="aaa"style="color: #8B1C62">你的用户名或密码错误！</span>')
                        alert('会话已失效！');
                        getValidCode();
                    }

                },
                error:function(data){
                    alert(data)
                    window.location.href="student.jsp";
                }
            });


    })

});