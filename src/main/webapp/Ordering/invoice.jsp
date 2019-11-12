<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<title>圆形区域搜索</title>
</head>
<body>
<div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");            // 创建Map实例

    var mPoint = new BMap.Point(101.840108,36.583227);
    map.enableScrollWheelZoom();
    map.centerAndZoom(mPoint,17);
    var mk = new BMap.Marker(mPoint);
    map.addOverlay(mk);

    var circle = new BMap.Circle(mPoint,200,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
    map.addOverlay(circle);
    var local =  new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}});
    showInfo()

    function showInfo(){
        // 用经纬度设置地图中心点
        var geolocation = new BMap.Geolocation();
        var new_point='';
        geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                var mk = new BMap.Marker(r.point);
                map.addOverlay(mk);
                map.panTo(r.point);


                new_point = new BMap.Point(r.point.lng,r.point.lat);
                var marker = new BMap.Marker(new_point);  // 创建标注
                map.addOverlay(marker);              // 将标注添加到地图中
                map.panTo(new_point);
            }
            else {
                alert('failed'+this.getStatus());
            }
        },{enableHighAccuracy: true})




    }

</script>