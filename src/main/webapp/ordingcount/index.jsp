<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
		<!-- Google Chrome Frame也可以让IE用上Chrome的引擎: -->
		<meta name="renderer" content="webkit">
		<!--国产浏览器高速模式-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="author" content="穷在闹市" />
		<!-- 作者 -->
		<meta name="revised" content="穷在闹市.v3, 2019/05/01" />
		<!-- 定义页面的最新版本 -->
		<meta name="description" content="网站简介" />
		<!-- 网站简介 -->
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>穷在闹市</title>

		<!-- 公共样式 开始 -->
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/iconfont.css">
		<script type="text/javascript" src="framework/jquery-1.11.3.min.js" ></script>
		<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
		<script type="text/javascript" src="layui/layui.js" ></script>
		<!-- 滚动条插件 -->
		<link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.css">
		<script src="framework/jquery-ui-1.10.4.min.js"></script>
		<script src="framework/jquery.mousewheel.min.js"></script>
		<script src="framework/jquery.mCustomScrollbar.min.js"></script>
		<script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->
		
		<!--报表-->
		<script type="text/javascript" src="framework/echarts.min.js" ></script>
		<link rel="stylesheet" type="text/css" href="css/backHome.css">
		<link rel="stylesheet" href="css/layui.css">
		<script type="text/javascript" src="js/backHome.js" ></script>
		<script type="text/javascript"  >

            $.ajax({
                url: "/Ordering/ordinginfo",
                type: 'post',
                dataType: "json",
                success: function (result) {
                    if (result.ordingcount >0) {
                        $("#ordingcount").text(result.ordingcount)
                        $("#ordingprice").text(result.ordingprice)
                    //
                     } else {
                        $("#ordingcount").text(0)
                        $("#ordingprice").text(0)
                     }
                     if(result.ordingcount2>0){
                         $("#ordingcount2").text(result.ordingcount2)
                         $("#ordingprice2").text(result.ordingprice2)
					 }else{
                         $("#ordingcount2").text(0)
                         $("#ordingprice2").text(0)
					 }
                },error: function (error) {

                }

            });
		</script>
	</head>

	<body >
		<div class="row">
			<div class="col-md-2">
				<div class="module" >


						
					<div class="title"><span></span><font>今日数据统计</font></div>
					<div class="content">
						<div class="today_data">
							<ul>
								<li class="new_bt">
									<div class="state"><i class="iconfont icon-xiangxiajiantoucuxiao"></i></div>
									<div class="data1">
										<p>今日订单数量</p>
										<h1 id="ordingcount">2</h1>
									</div>
									<div class="data2">
										<p>昨日订单量</p>
										<h1 id="ordingcount2">100</h1>
									</div>
								</li>
								<li>
									<div class="state"><i class="iconfont icon-xiangshangjiantoucuxiao"></i></div>
									<div class="data1">
										<p>今日订单总金额</p>
										<h1 id="ordingprice">2</h1>
									</div>
									<div class="data2">
										<p>昨日订单总金额</p>
										<h1 id="ordingprice2">5000</h1>
									</div>
								</li>

								<li>
									<div class="state">- - </div>
									<div class="data1">
										<p><a href="/goodsManage/goodslist.jsp">商品管理</a></p>

									</div>
									<div class="data2">
										<p><a href="/goodsManage/ordinglist.jsp">订单管理</a></p>

									</div>
								</li>

							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="module">
						<div class="title"><span></span><font>订单总量统计</font></div>
						<%--<span onclick="orderMapDataRefresha()" style="top: 2%;right:14% ;float: right;color: #21c667;margin: 4px;font-size: 6px">近24小时</span>--%>
						<span onclick="orderMapDataRefreshb()" style="top: 2%;right:14% ;float: right;color: #2d64fb;margin: 4px;font-size: 6px">近一周</span>
						<%--<span onclick="orderMapDataRefreshc()" style="top: 2%;right:10% ;float: right;color: #8f1311;margin: 4px;font-size: 6px">近一月</span>--%>
						<div class="content">
							<div id="echartsOrderNum" style="width: 100%;height:325px;"></div>
						</div>
					</div>
				</div>
				<div class="col-md-5 no_mr">
					<div class="module">
						<div class="title"><span></span><font>交易金额总量统计</font></div>
						<div class="content">
							<div class="today_data">
								<%--<span style="top: 2%;right:14% ;float: right;color: #21c667;margin: 4px;font-size: 6px">近24小时</span>--%>
								<span style="top: 2%;right:14% ;float: right;color: #2d64fb;margin: 4px;font-size: 6px">近一周</span>
								<%--<span style="top: 2%;right:10% ;float: right;color: #8f1311;margin: 4px;font-size: 6px">近一月</span>--%>
								<div id="echartsMoney" style="width: 100%;height:325px;"></div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md_5" >
				<div class="module">
					<div class="title"><span></span><font>销量统计</font></div>
					<div class="content">
						<div id="echartsMap" style="width: 100%;height:325px;"></div>
					</div>
				</div>
			</div>

		</div>

	</body>
	<script type="text/javascript" src="framework/china.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wWdTUDMYlX7BfX9bXQwuBET8g6FeHoT4"></script>
	<script>
        var dom = document.getElementById("echartsMap");
        var myChart = echarts.init(dom);
        var app = {};

        $.ajax({
            url: "/Ordering/querylistxl",
            type: 'post',
			dataType: "json",
            success: function (result) {
                var arr1 = new Array();
                var arr2 = new Array();
                var len = result.length;

                for (var i = 0; i < len; i++) {
                    arr1.push(result[i].foodname)
                    arr2.push(result[i].countt)



				}


                adddata(arr1,arr2)

            }

        })


		 // echartsMap("echartsMap",BJData);
        option = null;
        app.title = '世界人口总量 - 条形图';
        function adddata(arr1,arr2) {

            option = {
                title: {
                    text: '本月销量TOP10'

                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                // legend: {
                //     data: ['2011年', '2012年']
                // },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '1%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#9ac0cd'
                        }
                    },
                    axisLabel: {
                        color: '#9ac0cd'
                    },

                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#21c667'
                        }
                    },
                    axisLabel: {
                        color: '#9ac0cd'
                    },

                    data:
                    arr1
                        //["黑椒可乐牛排", "黑椒可乐牛排", "黑椒可乐牛排"]
                       // [" 黑椒可乐牛排"," 黄金香酥鸡块"," E"]
                },

                series: [
                    // {
                    //     name: '2011年',
                    //     type: 'bar',
                    //     data: [50, 40, 30, 20, 10, 100]
                    // },
                    {

                        type: 'bar',
                        itemStyle: {
                            normal: {
                                //好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
                                color: function (params) {
                                    // build a color map as your need.
                                    var colorList = [
                                        '#21c667', '#21c667', '#FCCE10', '#E87C25', '#27727B',
                                        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                                    ];
                                    return colorList[params.dataIndex]
                                },
                                //以下为是否显示，显示位置和显示格式的设置了
                                label: {
                                    show: false,
                                    position: 'top',
//                             formatter: '{c}'
                                    formatter: '{b}\n{c}'
                                }
                            }
                        },
                        barGap: '5',
                        barWidth: 20,
                        data: arr2
                    }
                ]
            };
            myChart.setOption(option, true);
        }
        function adddata1(arr1,arr2) {
            alert(arr1)
            echartsOrderNum("echartsOrderNum",arr2,arr1);
		}
		// var data = [820, 932, 901, 934, 1290, 1330, 1320];
		// var title = ['今天', '昨天', '1月15日', '1月16日', '1月17日', '1月18日', '1月19日'];
		function orderMapDataRefresh() {

            $.ajax({
                url: "/Ordering/weeklist",
                type: 'post',
                dataType: "json",
                success: function (result) {

                    var arr1 = new Array();
                    var arr2 = new Array();
                    var arr3 = new Array();
                    var len = result.length;
                    for (var i = 0; i < len; i++) {
                        var str = result[i].listdate.replace(/-/g,"").substr(4, 7);
                      // var aa=result[i].listprice.replace(/ /g,"0");
						var aa ="";
						if(result[i].listprice=="undefined"){
                           aa==0;
						}else{
						    aa=result[i].listprice;
						}
                        arr1.push(str)
                        arr2.push(result[i].listcount)
                        arr3.push(aa)

                    }

                    echartsOrderNum("echartsOrderNum",arr2,arr1);
                    echartsMoney("echartsMoney",arr3,arr1)
                }
            });

        }
        function orderMapDataRefreshb() {
            var data = [820, 932, 101, 934, 1290, 1330, 820];
            var title = ['一', '二', '三', '四', '五', '六', '天'];
            echartsOrderNum("echartsOrderNum",data,title);
        }
        function orderMapDataRefreshc() {
            var data= [820, 932, 101, 934, 1290, 1330, 820, 820, 932, 101, 934, 1290, 1330, 820];
            var title= ['1日', '2日', '3日', '4日', '4日', '6日', '7日', '8日', '9日', '10日', '11日', '12日', '13日', '14日'];
            echartsOrderNum("echartsOrderNum",data,title);


        }

		
		var data1 = [1120000, 15268423, 8729600, 1012500, 13220000, 1012500, 11320000, 15268423, 8729600, 1012500, 13220000, 1012500, 11320000];
		var title1 = ['1日', '2日', '3日', '4日', '4日', '6日', '7日', '8日', '9日', '10日', '11日', '12日', '13日'];
        echartsMoney("echartsMoney",data1,title1);
        orderMapDataRefresh();
	</script>
</html>