<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8" />
		<title>商品详情</title>
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
		<link rel="stylesheet" href="css/reset.css">
		<link rel="stylesheet" href="css/index.css">
		<!--导航栏渐变结束-->
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/swiper3.07.min.css" />
		<script src="js/jquery.js"></script>
		<script src="js/swiper.min.js"></script>
		<style>
			#slide {width: 100%; height:auto;overflow: hidden;position: relative;margin: 0;    height: 3rem;}
			#slide .swiper-slide {text-align: center;height: auto;}
			#tab_proList-com-det .goodsInfor .addToCart-com, #tab_proList-com-det .text_box, #tab_proList-com-det .decrease {
    top: 33%;
}
			#tab_proList-com-det li h2 a{color: #000;}
			#tab_proList-com-det li .price-com {
			    background-color: #ec7602;
			    padding: 0.06rem 0.2rem;
			    color: #fff;
			    border-radius: 1rem;
			    margin-left: -0.2rem;
			}
			.order-det-time, h4, p {
			    font-size: 0.3rem;
			    margin-bottom: 0.3rem;
			}

		</style>
		
		<script>
			$(document).ready(function() {
				var mySwiper = new Swiper('#slide', {
					autoplay: 5000,
					visibilityFullFit: true,
					loop: true,
					pagination: '.pagination',
				});
				//product list:Tab
				$(".tab_proList dd").eq(0).show().siblings(".tab_proList dd").hide();
				$(".tab_proList dt a").eq(0).addClass("currStyle");
				$(".tab_proList dt a").click(function() {
					var liindex = $(".tab_proList dt a").index(this);
					$(this).addClass("currStyle").siblings().removeClass("currStyle");
					$(".tab_proList dd").eq(liindex).fadeIn(150).siblings(".tab_proList dd").hide();
				});
				//飞入动画，具体根据实际情况调整
				
			});
		</script>
		
	</head>

	<body style="background-color: #f1f1f1;">
		<!--banner商品图-->
		<div id="slide" class="public-banner">
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
		</div>
		<!--商品详情-->
		<div class="Conmmodity">
			<span>
				<h1>糖醋里脊</h1>
				<p class="color999">月销288</p>
			</span>
			<span class="colorf00 f035">￥20</span>
		</div>
		<!--商品tab切换-->
		<div class="conmmodityTab clearfix">
			<a href="javascript:;" class="active">菜品介绍</a>
			<a href="javascript:;">配料准备</a>
			<div class="clearfix"></div>
		</div>
		<div class="conmmodity-container" >
			<div class="swiper-wrapper w">
				<div class="swiper-slide d">
					<div class="init-loading list-group-item text-center" style="display: none;">下拉可以刷新</div>
					<div class="swiper-container2">
						<div class="swiper-wrapper">
							<div class="swiper-slide list-group">
								<p>
									糖醋里脊是经典传统名菜之一，以猪肉里脊肉为主才，配以面粉，淀粉，醋等，酸甜可口，让人食欲大开。在浙菜，卤菜，川菜里均有此菜，菜品介绍
								</p>

							</div>
							<div class="swiper-slide list-group">
								<p>
									主辅料：猪里脊120克，胡萝卜100克，冬笋80克，黑木耳50克；调料：葱，姜，大蒜，草菇老抽，山西老陈醋，精盐，味精，白砂糖，生粉，郫县豆瓣酱，郫县泡椒酱适量。
								</p>
							</div>
						
						</div>
					</div>
				</div>
			</div>
			<div class="swiper-scrollbar"></div>
		</div>
		
		<!--评价-->
		<dl class="evaluateList">
			<dt class="evaluateList-title">
				<p class="f035">评价</p>
				<p class="f03">好评度<em class="colorf00">100%</em></p>
			</dt>
			<dd >	
				<div class="evaluate">
					<div class="evaluate-fl">
						<span><img src="img/index (1).png"></span>
						<span>
							<p>Ventilate</p>
							<p class="color999"> 2017-11-13</p>
						</span>
					</div>
					<div class="evaluate-fr">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
					</div>
				</div>
				<div class="evaluate-text">
					评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容
				</div>
			</dd>
			<dd >	
				<div class="evaluate">
					<div class="evaluate-fl">
						<span><img src="img/index (1).png"></span>
						<span>
							<p>Ventilate</p>
							<p class="color999"> 2017-11-13</p>
						</span>
					</div>
					<div class="evaluate-fr">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
						<img src="img/shoucang1.png">
					</div>
				</div>
				<div class="evaluate-text">
					评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容
				</div>
			</dd>
		</dl>
		
		
		
		<!--商品详情页的banner图片位置-->			
		<script type="text/javascript">
			$("#slide").find("img").each(function(){
				var w=screen.width;
				console.log(w) ;
				$(this).css("width",w+"px") ;
				console.log($(this).width()) ;
				$(this).css("height",w+"px") ;
				
			})
		</script>	
		<script>
			document.oncontextmenu = new Function("event.returnValue=false;");
			document.onselectstart = new Function("event.returnValue=false;");
		</script>
		<script type="text/javascript">
			var loadFlag = true;
			var oi = 0;
			var mySwiper = new Swiper('.conmmodity-container', {
				direction: 'vertical',
				scrollbar: '.swiper-scrollbar',
				slidesPerView: 'auto',
				mousewheelControl: true,
				freeMode: true,
				onTouchMove: function(swiper) { //手动滑动中触发
					var _viewHeight = document.getElementsByClassName('swiper-wrapper')[0].offsetHeight;
					var _contentHeight = document.getElementsByClassName('swiper-slide')[0].offsetHeight;
				},
				
			});
			var mySwiper2 = new Swiper('.swiper-container2', {
				onTransitionEnd: function(swiper) {

					$('.w').css('transform', 'translate3d(0px, 0px, 0px)')
					$('.swiper-container2 .swiper-slide-active').css('height', 'auto').siblings('.swiper-slide').css('height', '0px');
					mySwiper.update();

					$('.conmmodityTab a').eq(mySwiper2.activeIndex).addClass('active').siblings('a').removeClass('active');
				}
			});
			$('.conmmodityTab a').click(function() {
				$(this).addClass('active').siblings('a').removeClass('active');
				mySwiper2.slideTo($(this).index(), 500, false)

				$('.w').css('transform', 'translate3d(0px, 0px, 0px)')
				$('.swiper-container2 .swiper-slide-active').css('height', 'auto').siblings('.swiper-slide').css('height', '0px');
				mySwiper.update();
			});
		</script>
		
	</body>

</html>