<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8"/>
	<title>发票订单</title>
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
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" href="css/shopping_cartt.css">	
    <title>发票订单</title>
</head>
<body>
<div class="main"> 
	<ul>
		<li class="invoiceOrder-list clearfix">
			<div  class="commodity"></div>
			<div class="swiper-slide list-group">
				<div class="order-details-header">
					<div class="order-det-logo fl"><img src="img/logo.jpg"></div>
					<div class="order-det-time fl">
						<h4>合肥南站店</h4>
						<p>2017-10-12</p>
					</div>
					<div class="my-order-iphone1 fr f032 text-right">
						<h4 class="f029">￥24.50</h4>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="commodity-name f032">			
					<span>商品名称1<em>×</em>2</span>+<span>商品名称1<em>×</em>2</span>
					<span>商品名称1<em>×</em>2</span>+<span>商品名称1<em>×</em>2</span>			
				</div>
			</div>
		</li>
		<li class="invoiceOrder-list clearfix">
			<div class="commodity"></div>
			<div class="swiper-slide list-group">
				<div class="order-details-header">
					<div class="order-det-logo fl"><img src="img/logo.jpg"></div>
					<div class="order-det-time fl">
						<h4>合肥南站店</h4>
						<p>2017-10-12</p>
					</div>
					<div class="my-order-iphone1 fr f032 text-right">
						<h4 class="f029">￥24.50</h4>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="commodity-name f032">			
					<span>商品名称1<em>×</em>2</span>+<span>商品名称1<em>×</em>2</span>
					<span>商品名称1<em>×</em>2</span>+<span>商品名称1<em>×</em>2</span>			
				</div>
			</div>
		</li>
	</ul>
</div>
<div class="pay1">    
    <a href="javascript:void(0)" style="width: 100%;">确定</a>
</div>

<script src="js/swiper.min.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
   $(".commodity").click(function(){
    if($(this).hasClass("select")){
    	 $(this).removeClass(" select") ;
    }else{
    	 $(this).addClass(" select") ;
    }
});
</script>
</body>
</html>