<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8" />
		<title>服务</title>
		<meta name="keywords" content="KEYWORDS..." />
		<meta name="description" content="DESCRIPTION..." />
		<meta name="author" content="DeathGhost" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name='apple-touch-fullscreen' content='yes'>
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name="format-detection" content="address=no">
		<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
		<!--导航栏渐变开始-->
		<!--导航栏渐变结束-->
		<link rel="stylesheet" type="text/css" href="css/swiper3.07.min.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />

		<script src="js/jquery.js"></script>
		<script src="js/swiper.min.js"></script>
		<style>
			.addclasstruenav{color: #3acd17;}
		</style>
	</head>

	<body style="background-color: #fff;">
		<!--banner-->
		<div id="slide" class="public-banner" style="margin-top: 0;">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<a href="#">
						<img src="upload/banner1.jpg" />
					</a>
				</div>
				<div class="swiper-slide">
					<a href="#">
						<img src="upload/banner2.jpg" />
					</a>
				</div>
			</div>
			<div class="pagination"></div>
		</div>

		<ul class="serviceList clearfix">
			<li>
				<a href="wifi.jsp">
					<img src="img/fuwu (1).png" />
					<p>查WIFI</p>
				</a>				
			</li>
			<li>
				<a href="tableware.jsp">
					<img src="img/fuwu (2).png" />
					<p>要餐具</p>
				</a>				
			</li>
			<li>
			<a href="zuowei.jsp">
					<img src="img/fuwu (3).png" />
					<p>找位子</p>
				</a>
				
			</li>			
		</ul>
		
		<ul class="serviceList clearfix" style="margin: 0;">			
			<li>
				<a href="call-phone.jsp">
					<img src="img/fuwu (4).png" />
					<p>服务员</p>
				</a>				
			</li>
			<li>
				<a href="invoice.jsp">
					<img src="img/fuwu (5).png" />
					<p>发票</p>
				</a>				
			</li>
			<li>
				<a href="classification.jsp">
					<img src="img/fuwu (6).png" />
					<p>打包</p>
				</a>				
			</li>
		</ul>
		<div style="height:1.2rem;"></div>
		<nav>
			<a href="index.jsp" class="homeIcon "><img src="img/index1.png">点餐</a>
			<%--<a href="my-order.jsp" class="categoryIcon "><img src="img/index2.png">订单</a>--%>
			<a href="service.jsp" class="cartIcon numberCount addclasstruenav"><img src="img/index3-1.png">服务</a>
			<a href="about-us.jsp" class="userIcon"><img src="img/index4.png">我的</a>
		</nav>
		
		
		
		<!--banner-->
		<script>
			$(document).ready(function() {
				var mySwiper = new Swiper('#slide', {
					autoplay: 5000,
					visibilityFullFit: true,
					loop: true,
					pagination: '.pagination',
				});
			});
		</script>
		</body>

</html>