<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8" />
		<title>我的积分</title>
		<meta name="keywords" content="KEYWORDS..." />
		<meta name="description" content="DESCRIPTION..." />
		<meta name="author" content="DeathGhost" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name='apple-touch-fullscreen' content='yes'>
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name="format-detection" content="address=no">
		<link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="images/icon/apple-touch-icon-57x57-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="120x120" href="images/icon/apple-touch-icon-120x120-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="196x196" href="images/icon/apple-touch-icon-196x196-precomposed.png">
		<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/reset.css" />
		<script src="js/jquery.js"></script>
	</head>

	<body style="background-color: #fff;">
		<!--	<header>
		<a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
		<h1>余额</h1>
	</header>
	<div style="height:1rem;"></div>-->
		<div class="clearfix integral-title">
			<span>
			<h2 class="f032 text-center padb03 bg-fff font-weight400 color999">您当前积分为（分）</h2>
			<h1 class="f042 text-center bg-fff"><em class="">50</em></h1>		
		</span>
			<span>
			<h2 class="f032 text-center padb03 bg-fff font-weight400 color999">可抵用（元）</h2>
			<h1 class="f042 text-center bg-fff"><em class="">0.50</em></h1>		
		</span>
		</div>

		<table class="balaner-table bor-top5-f1">
			<thead>
				<tr>
					<td>时间</td>
					<td>新增积分</td>
					<td>减少积分</td>
					<td>剩余积分</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>2017-10-24 10:10</td>
					<td>+0</td>
					<td>-50</td>
					<td>50</td>
				</tr>
				<tr>
					<td>2017-10-24 10:10</td>
					<td>+0</td>
					<td>-50</td>
					<td>50</td>
				</tr>
				<tr>
					<td>2017-10-24 10:10</td>
					<td>+0</td>
					<td>-50</td>
					<td>50</td>
				</tr>
			</tbody>
		</table>
	</body>

</html>