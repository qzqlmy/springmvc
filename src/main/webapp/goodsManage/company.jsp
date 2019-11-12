<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ECharts</title>
	<!-- 引入 echarts.js -->
	<script src="framework/echarts.min.js"></script>
	<script src="framework/walden.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div style="width: 1000px;height:400px;">
	<div id="main" style="width: 400px;height:400px;"></div>
	<div id="container" style="width: 800px;height:400px;right: 100%"></div>
</div>

<script type="text/javascript">

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: '入门示例'
        },
        color:['#3f555f','#2baaac'],	//调色盘颜色列表
        legend: {
            top: 42,
            right: 0,
            textStyle: {
                color: '#21c667'
            },
            icon: 'roundRect',
            data: ['下单统计']
        },
        //直角坐标系内绘图网格
        // grid: {
        //     top: 100,
        //     bottom: 30,
        //     left: 60,
        //     right: 30
        // },
        xAxis : {
            axisLine : {
                show: true,
                lineStyle : {
                    color: '#010232'
                }
            },
            type : 'category',
            boundaryGap : false,	//坐标轴两边留白策略
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun', '0629', '0630', '20190701']
        },
        yAxis: {
            type: 'value',
            axisLine : {
                show: true,
                lineStyle : {
                    color: '#21c667'
                }
            },
            axisLabel: {
                color: '#9ac0cd'
            },
            axisPointer: {
                snap: true
            }
        },
        series : [
            {
                name:'下单统计',
                type:'line',
                smooth: false,	//是否平滑曲线显示
                symbolSize: 5,
                itemStyle: {	//折线拐点标志的样式
                    borderWidth: 2,
                    borderColor: '#6884f4'
                },
                lineStyle: {	//线条样式
                    color: '#303030',
                    width: 3
                },
                areaStyle: {	//区域填充样式
                    color: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 0,
                        y2: 1,
                        colorStops: [{
                            offset: 0, color: '#3e555f' // 0% 处的颜色
                        }, {
                            offset: 1, color: '#4a586e' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    }
                },
                data: [820, 932, 901, 934, 1290, 1330, 1320, 1320, 1320, 2720],
                type: 'line'
            }]







    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'), 'wonderland');

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{

            name: '销量',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>