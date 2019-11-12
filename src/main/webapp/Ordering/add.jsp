<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/29 0029
  Time: 下午 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加菜单</title>
</head>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery.tips.js"></script>

<body>
类型：<select id="test" class="js-example-basic-multiple" name='local' >
    <option value='0'>请选择</option>
    <option value='1'>热销</option>
    <option value='2'>荤菜</option>
    <option value='3'>素菜</option>
    <option value='4'>凉菜</option>
    <option value='5'>今日促销</option>

</select>
菜名: <input id="foodname" width="5px;!important">
价格: <input id="price" width="5px;!important">
<input id="hh" style="display: none" type="file" onchange="setImg(this);"/>
上传样例：<img  id="cccf"
     src="http://localhost:8081/upload/imgs/20190528\1559013868085_909.png" width="30" height="30"/>


</body>
<script >
    $("#cccf").click(function () {
        if ($("#foodname").val() == "") {

            $("#foodname").tips({
                side: 1,
                msg: '请输入菜名',
                bg: '#AE81FF',
                time: 3
            });
            return false;
        }
        if ($("#price").val() == "") {

            $("#price").tips({
                side: 1,
                msg: '请输入价格',
                bg: '#AE81FF',
                time: 3
            });
            return false;
        }
        $("#hh").click();


    })
    function setImg(obj) {
        var f = $(obj).val();
        var sf = $("#test").val();
        var aa= $("#foodname").val();
        var price= $("#price").val();
        console.log(sf+aa);
        if (f == null || f == undefined || f == '') {
            return false;
        }
        if (!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
            alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
            $(obj).val('');
            return false;
        }
        var data = new FormData();
        console.log(data);
        $.each($(obj)[0].files, function (i, file) {
            data.append('file', file);
            console.log(file);
        });
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/Uploader/uploadImg",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            dataType: "json",
            success: function (ret) {
                console.log(ret);
                if (ret.code == 0) {
                    var url=ret.result.url;
                    // alertOk(ret.message);
                    $("#hh").hide();
                    $.ajax({
                        url: "/Ordering/foodcreate",
                        type: 'post',
                        data: {"leixin": sf, "foodname": aa, "src": url, "price":price},
                        dataType: "json",
                        success: function (result) {
                            if (result.code ==0) {
                                alert("创建成功")
                                window.location.href="add.jsp";
                            } else {
                                alert("创建失败")
                            }
                         },
                        error: function (error) {
                            alert('页面加载异常!' + error);
                        }
                    });
                } else {
                    alertError(ret.message);
                    $("#url").val("");
                    $(obj).val('');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
            }
        });

    }
</script>
</html>
