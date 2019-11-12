<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       <script src="framework/echarts.min.js"></script>
       <script src="framework/walden.js"></script>
       <script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '世界人口总量 - 条形图';

option = {
    title: {
        text: '世界人口总量',
        subtext: '数据来自网络'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    legend: {
        data: ['2011年', '2012年']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '1%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        axisLine : {
            show: true,
            lineStyle : {
                color: '#9ac0cd'
            }
        },
        axisLabel: {
            color: '#9ac0cd'
        },

        boundaryGap: [0,0.01]
    },
    yAxis: {
        type: 'category',
        axisLine : {
            show: true,
            lineStyle : {
                color: '#21c667'
            }
        },
        axisLabel: {
            color: '#9ac0cd'
        },

        data: ['巴西','印尼','美国','印度','中国','世界人口(万)']
    },

    series: [
        // {
        //     name: '2011年',
        //     type: 'bar',
        //     data: [50, 40, 30, 20, 10, 100]
        // },
        {
            name: '2012年',
            type: 'bar',
            itemStyle: {
                normal: {
                    //好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#21c667','#21c667','#FCCE10','#E87C25','#27727B',
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                            '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                        ];
                        return colorList[params.dataIndex]
                    },
                    //以下为是否显示，显示位置和显示格式的设置了
                    label: {
                        show: true,
                        position: 'top',
//                             formatter: '{c}'
                        formatter: '{b}\n{c}'
                    }
                }
            },
            barGap:'5',
            barWidth:20,
            data: [5, 20, 30, 35, 40, 50]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
   </body>
</html>