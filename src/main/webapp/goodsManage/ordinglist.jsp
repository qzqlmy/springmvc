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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
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
    <title>订单管理</title>

    <!-- 公共样式 开始 -->
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconfont.css">

    <script type="text/javascript" src="framework/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <script type="text/javascript" src="layui/layui.js"></script>
    <script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
    <link type="text/css" rel="stylesheet" href="css/jeDate-test.css">
    <link type="text/css" rel="stylesheet" href="css/jedate.css">
    <script type="text/javascript" src="js/jedate.js"></script>
    <script type="text/javascript" src="js/crypto-js.js"></script>
    <!-- 公共样式 结束 -->

    <style>
        .itemdemo {
            width: 100%;
            float: left;
            padding: 12px;
            border-bottom: 1px #DFDFDF solid;
        }
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
    <script type="text/javascript">
        var start = {}, end = {};
        jeDate('#inpstart',{
            format: 'YYYY-MM-DD hh:mm:ss',
            minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
            maxDate: function (that) {
                //that 指向实例对象
                return jeDate.valText(that.valCell) == "" ? jeDate.nowDate({DD:0}) : start.maxDate;
            }, //设定最大日期为当前日期
            donefun: function(obj){
                end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
                jeDate("#inpend",LinkageEndDate(false));
            }
        });
        jeDate('#inpend',LinkageEndDate);

        function LinkageEndDate(istg) {
            return {
                trigger : istg || "click",
                format: 'YYYY-MM-DD hh:mm:ss',
                minDate: function (that) {
                    //that 指向实例对象
                    var nowMinDate = jeDate.valText('#inpstart') == "" && jeDate.valText(that.valCell) == "";
                    return nowMinDate ? jeDate.nowDate({DD:0}) : end.minDate ;
                }, //设定最小日期为当前日期
                maxDate: '2099-06-16 23:59:59', //设定最大日期为当前日期
                donefun: function(obj){
                    start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
                }
            };
        }
    </script>
</head>

<body>
<div class="cBody">
    <div class="console">
        <form class="layui-form" action="">
            <input type="text" style="width:50%;display: none"class="jeinput" value="ordinglist" id="pagedata">
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" name="name" id="foodname" placeholder="输入商品名称"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="name" id="ording" placeholder="输入订单号"
                           autocomplete="off" class="layui-input">
                </div>
                    <%--<input type="text" style="width:50%"class="jeinput" id="foodname" placeholder="输入商品名称">--%>
                    <%--<p><br>--%>
                        <%--<input type="text" style="width:50%"class="jeinput"  id="ording" placeholder="输入订单号"><p><br>--%>
                    <input type="text" style="width:50%;display: none"class="jeinput" id="foodnme" required lay-verify="required" placeholder="输入商品名称">
                <input type="text" style="width:50%" class="jeinput" id="test04" placeholder="选择开始时间">

                <p><br>
                    <input type="text" style="width:50%" class="jeinput" id="test05" placeholder="选择结束时间">
                <button class="layui-btn"  onclick=" query()">订单查询</button>
            </div>
        </form>

        <script>

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
            <th>订单号</th>
            <th>商品名称</th>

            <th>商品价格</th>

            <th>商品数量</th>

            <th>下单时间</th>
        </tr>
        </thead>

        <tbody id="dddddddd">
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
                var ording = $("#ording").val();
                var starttime = $("#test04").val();
                var endttime = $("#test05").val();
                var aseKey = "7E174BEFA9F62C846F61BD441DC41E62"     //秘钥必须为：8/16/32位
                var message = "80018000142";
                //加密
                var data={
                    'foodname': foodname,
                    'ording': ording,
                    'starttime': starttime,
                    'endttime': endttime
                }
                var encrypt = CryptoJS.AES.encrypt(JSON.stringify(data), CryptoJS.enc.Utf8.parse(aseKey), {
                    mode: CryptoJS.mode.ECB,
                    padding: CryptoJS.pad.Pkcs7
                }).toString();

                $.ajax({
                    url: "/Ordering/querylistcount",
                    type: 'post',
                    data: {"encrypt": encrypt},
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
    <script type="text/javascript" src="js/demo.js"></script>
</div>
</body>

</html>
