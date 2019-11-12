<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8" />
		<title>订单结算</title>
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
		<link rel="stylesheet" type="text/css" href="css/reset.css">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.min.js" ></script>
		<script type="text/javascript" src="js/date.js" ></script>
		<script type="text/javascript" src="js/iscroll.js" ></script>
		<script>
			$(function() {
				var all = $("#time").html();
				var m = Number(all.substring(0, all.indexOf(":")));
				var s = Number(all.substring(all.indexOf(":") + 1, all.length + 1));
				var f = setInterval(function() {
					if(s < 10) {
						//如果秒数少于10在前面加上0
						$('#time').html(m + ':0' + s);
					} else {
						$('#time').html(m + ':' + s);
					}
					s--;
					if(s < 0) {
						//如果秒数少于0就变成59秒
						if(m==0){
							window.clearInterval(f); 
						}
						m--;
						s = 59;
					}
                    var nm = $("#time").html();


                    if(nm=='0:00'){
                        window.location.href = "index.jsp"
                    }
				}, 1000)
			})
			/*支付标签切换*/
			$(function(){
				$(".payway").click(function(){
					$(".fr>img").attr("src","img/check (1).png");
					$(this).find(".fr>img").attr("src","img/check (2).png") ;
				})
			})
		</script>

	<script type="text/javascript">
		$(function(){
			$('#beginTime').date();
			$('#endTime').date({theme:"datetime"});
		});
	</script>
	</head>

	<body style="overflow-y: scroll;height: 100%;">
		<div class="pay-order-header">
			<ul>
				<li>支付剩余时间</li>
				<li id="time">3:00</li>
			</ul>
		</div>
		
		<div class="order-details-header clearfix">			
			<h4 class="fl">就餐方式</h4>	
			<select class="fr">
				<option>堂吃</option>
				<option>打包</option>

				<option>外卖</option>
			</select>
		</div>

		<table width="100%" class="bg-fff order-det-cont" >
			<tr>
				<td align="left" class="padl3" style="color:#999">订单号</td>
				<td align="right" width="50%" style="color:#999">${sessionScope.ordinginfo.ordingid}</td>
			</tr>
			<tr>
				<td align="left" width="60%" >商品名称</td>
				<td align="left" width="15%"><em>价格</em></td>
				<td align="left" width="15%"><em>数量</em></td>
			</tr>
			<tbody>

			<c:forEach items="${sessionScope.ordinglist}" var="ording" varStatus="vs">
				<tr>
					<td align="left" width="60%">${ording.foodname}</td>
					<td align="left" width="15%"><em>${ording.foodprice}</em></td>
					<td align="left" width="15%">×<em>${ording.foodcount}</em></td>
				</tr>
			</c:forEach>




			</tbody>
			<%--<tr>--%>
				<%--<td align="left" colspan="1" class="padl3">餐盒费</td>--%>
				<%--<td align="right" class="padr3">￥<em>1</em></td>--%>
			<%--</tr>--%>
			<tr>
				<td align="left" colspan="1" class="padl3">餐品总额</td>
				<td align="right" class="padr3">￥<em>${sessionScope.ordinginfo.price}</em></td>
			</tr>
		</table>
		
		<div style="height:1rem;"></div>
		<div class="order-set-paybutton">
			<div class="paybutton-left fl" style="width: 40%;text-align: center;"><a href="index.jsp">取消</a></div>
			<div class="paybutton-right fr" style="width: 60%;text-align: center;"><a href="#">确认付款</a></div>
			<div class="clearfix"></div>
		</div>
<div id="datePlugin"></div>
	</body>
	<script type="text/javascript">

	</script>
</html>