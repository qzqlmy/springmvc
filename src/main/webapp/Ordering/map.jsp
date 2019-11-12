<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:500px;width:100%;}
		#r-result{width:100%; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Kqt1a54AYfWQBzUByDWZ2hFzQNsboZaP"></script>
	<title>城市名定位</title>
</head>
<body>
<div id="allmap"></div>
<div id="r-result">
	<%--经度: <input id="longitude" type="text" style="width:100px; margin-right:10px;" />--%>
	<%--纬度: <input id="latitude" type="text" style="width:100px; margin-right:10px;" />--%>
	<%--<input type="button" value="查询" onclick="theLocation()" />--%>
</div>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(101.7552530000,36.6863250000),19);
    map.enableScrollWheelZoom(true);




    // 用经纬度设置地图中心点
    var geolocation = new BMap.Geolocation();
    var new_point='';
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            var mk = new BMap.Marker(r.point);
            map.addOverlay(mk);
            map.panTo(r.point);


            new_point = new BMap.Point(r.point.lng,r.point.lat);
        }
        else {
            alert('failed'+this.getStatus());
        }
    },{enableHighAccuracy: true})

            map.clearOverlays();

            var marker = new BMap.Marker(new_point);  // 创建标注
            // map.addOverlay(marker);              // 将标注添加到地图中
            map.panTo(new_point);
            var new_pointa='';
    function showInfo(e){

        new_pointa = new BMap.Point(e.point.lng ,e.point.lat);
        var marker = new BMap.Marker(new_pointa);

        map.addOverlay(marker);
        aaaa(new_pointa,new_point);

    }
   map.addEventListener("click", showInfo);
    function aaaa(pointA,pointB){
        map.clearOverlays();
        var marker1 = new BMap.Marker(pointA);
        var marker2 = new BMap.Marker(pointB);
        map.addOverlay(marker1);
        map.addOverlay(marker2);
        alert('距离是：'+(map.getDistance(pointA,pointB)).toFixed(2)+' 米。');  //获取两点距离,保留小数点后两位
        var polyline = new BMap.Polyline([pointA,pointB], {strokeColor:"blue", strokeWeight:6, strokeOpacity:0.5});  //定义折线
        map.addOverlay(polyline);
    };

</script>

