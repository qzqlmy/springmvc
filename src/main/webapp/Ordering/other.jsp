<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:500px;}
		p{margin-left:5px; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Kqt1a54AYfWQBzUByDWZ2hFzQNsboZaP"></script>
	<title>纯文本的信息窗口</title>
</head>
<body>
<div id="allmap"></div>
<p>点击标注点，可查看由纯文本构成的简单型信息窗口</p>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    var point1 = new BMap.Point(101.7552530000,36.6863250000);
    map.centerAndZoom(point1,12);

    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);

            var  new_point = new BMap.Point(r.point.lng,r.point.lat);
            showInfo(new_point)
        }
        else {
            alert('failed'+this.getStatus());
        }
    },{enableHighAccuracy: true})
    function showInfo(point){
        var marker = new BMap.Marker(point);  // 创建标注
        map.addOverlay(marker);              // 将标注添加到地图中
        map.centerAndZoom(point, 15);
        var opts = {
            width : 200,     // 信息窗口宽度
            height: 100,     // 信息窗口高度
            title : "你祁爷爷等你来哦！" , // 信息窗口标题
            enableMessage:true,//设置允许信息窗发送短息
            message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
        }
        var infoWindow = new BMap.InfoWindow("地址：御香苑火锅(旗舰店)青海省西宁市城北区小桥大街28号", opts);  // 创建信息窗口对象

            map.openInfoWindow(infoWindow,point); //开启信息窗口

	}

</script>

