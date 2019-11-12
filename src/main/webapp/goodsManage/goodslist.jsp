<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/30 0030
  Time: 下午 8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
    <meta name="renderer" content="webkit">
    <!--国产浏览器高速模式-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="sys"/>
    <!-- 作者 -->
    <meta name="revised" content="sys.v3, 2019/05/01"/>
    <!-- 定义页面的最新版本 -->
    <meta name="description" content="网站简介"/>
    <!-- 网站简介 -->
    <meta name="keywords" content="搜索关键字，以半角英文逗号隔开"/>
    <title>商品清單管理</title>

    <!-- 公共样式 开始 -->
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconfont.css">

    <script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script type="text/javascript" src="layui/layui.js"></script>
    <script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->


    <!-- 公共样式 结束 -->

    <style>
        .layui-table img {
            max-width: none;
        }
        .layui-layer {
            -webkit-overflow-scrolling: touch;
            top: 50%;
            left: 0;
            margin: 0;
            padding: 0;
            background-color: #fff;
            -webkit-background-clip: content;
            border-radius: 2px;
            box-shadow: 1px 1px 50px rgba(0,0,0,.3)
            !important;
        }
    </style>

</head>

<body>
<div class="cBody">
    <div class="console">
        <form class="layui-form" action="">
            <input type="text" style="width:50%;display: none"class="jeinput" value="goodslist" id="pagedata">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="name" id="foodname" required lay-verify="required" placeholder="输入商品名称"
                            class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="name" id="price" required lay-verify="required" placeholder="输入商品价格"
                           class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <select name="cityid" id="test" lay-filter="cityid">
                        <option value=''>请选择分类</option>
                        <option value='1'>热销</option>
                        <option value='2'>荤菜</option>
                        <option value='3'>素菜</option>
                        <option value='4'>凉菜</option>
                        <option value='5'>今日促销</option>
                    </select>
                </div>
                <button class="layui-btn"  onclick=" query()">商品查询</button>
                <a class="layui-btn " href="goodsAdd.jsp">商品新增</a>

            </div>
        </form>

        <script>
            $.ajax({
                url: "/Ordering/queryfoodlist",
                type: 'post',
                dataType: "json",
                success: function (result) {


                }

            })
            layui.use('form', function () {
                var form = layui.form;

                //监听提交
                form.on('submit(submitBut)', function (data) {
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
            });
        </script>
    </div>

    <table class="layui-table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>商品图片</th>
            <th>商品价格</th>

            <th>商品分类</th>

            <th>操作</th>
        </tr>
        </thead>
        <tbody id="ffffffff">
        <%--<c:forEach items="${sessionScope.foodlist}" var="food" varStatus="vs">--%>
            <%--<tr>--%>
                <%--<td>${food.foodname}</td>--%>
                <%--<td><img src="${food.foodurl}" width="20" height="20" onmouseenter="imgBig(this)"--%>
                         <%--onmouseleave="imgSmall(this)"/></td>--%>
                <%--<td>${food.price}</td>--%>
                <%--<td>${food.foodmode2}</td>--%>
                <%--<td>--%>

                    <%--<a class="layui-btn layui-btn-xs" href="/Ordering/editor/${food.foodname}">修改</a>--%>
                    <%--<a class="layui-btn layui-btn-xs" onclick=" del('${food.foodname}')">删除</a>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        </tbody>
    </table>

    <!-- layUI 分页模块 -->

    <div id="page" style="top: 100%;">

    </div>
    <script>

        query();
        function query() {
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                var foodname=$("#foodname").val();
                var test = $("#test").val();
                var price= $("#price").val();

                $.ajax({
                    url: "/Ordering/querycount",
                    type: 'post',
                    data: {"foodname": foodname, "test": test, "price":price},
                    dataType: "json",
                    success: function (result) {

                       var paged=result.count

                        //总页数大于页码总数
                        laypage.render({
                            elem: 'page'
                            , count: paged
                            , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                            , jump: function (obj) {
                                //					      console.log(obj)
                            }
                        });
                    }

                })

            });
        }
        $(".layui-laypage-prev").click(function () {
            alert(1111)
        })

        //删除
        function del(str) {

            layui.use(['form', 'laydate'], function () {
                layer.confirm('确定要删除么？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    layer.msg('删除成功', {
                        icon: 1
                    });
                    window.location.href = "/Ordering/remove/" + str;

                }, function () {
                    layer.msg('取消删除', {
                        time: 2000 //20s后自动关闭
                    });
                });
            });
        }

        //修改规格
        function specificationsBut() {
            layui.use('layer', function () {
                var layer = layui.layer;

                //iframe层-父子操作
                layer.open({
                    type: 2,
                    area: ['70%', '60%'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: 'specifications_list.html'
                });
            });

        }

        //修改按钮
        var updateFrame = null;

        function updateBut() {
            layui.use('layer', function () {
                var layer = layui.layer;

                //iframe层-父子操作
                updateFrame = layer.open({
                    title: "商品新增",
                    type: 2,
                    area: ['70%', '60%'],
                    scrollbar: false,	//默认：true,默认允许浏览器滚动，如果设定scrollbar: false，则屏蔽
                    maxmin: true,
                    content: 'goodsAdd.jsp'
                });
            });

        }

    </script>
</div>
</body>

</html>
