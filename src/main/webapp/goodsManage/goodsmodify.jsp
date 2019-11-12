<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/1 0001
  Time: 上午 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="sys" />
    <!-- 作者 -->
    <meta name="revised" content="sys.v3, 2019/05/01" />
    <!-- 定义页面的最新版本 -->
    <meta name="description" content="网站简介" />
    <!-- 网站简介 -->
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
    <title>商品信息修改</title>

    <!-- 公共样式 开始 -->
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconfont.css">
    <script src="framework/jquery-1.11.3.min.js"></script>
    <script src="js/jquery.tips.js"></script>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script type="text/javascript" src="layui/layui.js"></script>
    <script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
    <!-- 公共样式 结束 -->

    <style>
        .layui-form-label{
            width: 100%px;
        }
        .layui-input-block{
            margin-left: 130px;
        }
        .layui-form{
            margin-right: 0%;
        }
    </style>

</head>

<body>
<div class="cBody">
    <form id="addForm" class="layui-form" action="">

        <div class="layui-form-item">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <input type="text" id="foodname" name="goodsName" value="${sessionScope.foodlist.foodname}" required lay-verify="required" autocomplete="off" class="layui-input">
               <br>

            </div>

        </div>

        <input id="hhff" type="text" value="${sessionScope.foodlist.foodurl}" style="display: none"/>
        <input id="hh" style="display: none" type="file" onchange="setImg(this);"/>
        <div class="layui-form-item">
            <label class="layui-form-label">商品图片</label>
            <div class="layui-upload-drag" id="goodsPic">

                <img id="phk"
                     src="${sessionScope.foodlist.foodurl}" width="100" height="100"/>
                <p id="uplode">点击上传，或将文件拖拽到此处</p>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">参考价格</label>
            <div class="layui-input-block">
                <input type="text" name="price" value="${sessionScope.foodlist.price}" id="price" required lay-verify="required|number" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">规格</label>
            <div class="layui-input-block">
                <input type="password" name="password2" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="desc" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-inline">
                <select id="test" class="js-example-basic-multiple" name='local' >
                    <option value='0'>请选择</option>
                    <option value='1'>热销</option>
                    <option value='2'>荤菜</option>
                    <option value='3'>素菜</option>
                    <option value='4'>凉菜</option>
                    <option value='5'>今日促销</option>

                </select>
            </div>

        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <span class="layui-btn" id="aaaa">立即提交</span>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>


</div>
</body>
<script >


    $("#aaaa").click(function () {
        var url=$("#hhff").val();
        var sf = $("#test").val();
        var aa= $("#foodname").val();
        var price= $("#price").val();
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

        $.ajax({
            url: "/Ordering/foodupdate",
            type: 'post',
            data: {"leixin": sf, "foodname": aa, "src": url, "price":price},
            dataType: "json",
            success: function (result) {
                window.location.href="/layui/three.html";
            },error: function (error) {
                window.location.href="/layui/three.html";
            }

        });
    });
    $("#uplode").click(function () {

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
                    $("#hhff").val(ret.result.url);//将地址存储好
                    $("#phk").attr("src", ret.result.url);//显示图片

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
