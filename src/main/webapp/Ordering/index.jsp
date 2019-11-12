<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name='apple-touch-fullscreen' content='yes'>
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="address=no">
	<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
	<title>点餐系统</title>

	<!--导航栏渐变开始-->
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/swiper3.07.min.css" />
	<link rel="stylesheet" href="css/index.css">
	<script src="js/jquery.js"></script>
	<script src="js/swiper.min.js"></script>
    <script type="text/javascript">

        // table ="<ul>";
        // var h="";


    </script>
	<style>
		nav a {
			display: block;
			-webkit-box-flex: 1;
			-moz-box-flex: 1;
			text-align: center;
			color: grey;
			line-height: 1.2;
			color: #666;
			text-shadow: 0 1px 1px white;
		}
		.addclasstruenav{color: #3acd17;}


	</style>
	<!--地区参数传值-->

</head>

<body>
<!--header-->
<header >
	<a href="scanCode.jsp" class="location" id="location" data-title-type="native">
		<img src="img/sys.png" />
	</a>

	<div class="top-sch-box flex-col logoIcon">
		<a>
			<aside class="index-searchArea">
				<input class="input-text-searchArea" type="text" placeholder="请输入餐品" />
				<input type="button" value="&#63;" class="input-searchBtn" />
			</aside>
		</a>
	</div>
	<a class="rt_searchIcon" href="invoice.jsp"><img style="width:70%;" src="img/mapIcon.png"></a>
</header>

<!--slide-->
<div id="slide" class="public-banner">
	<div class="swiper-wrapper">
		<div class="swiper-slide">
			<a href="#">
				<img src="upload/banner1.jpg" />
			</a>
		</div>
		<%--<div class="swiper-slide">--%>
			<%--<a href="#">--%>
				<%--<img src="upload/banner2.jpg" />--%>
			<%--</a>--%>
		<%--</div>--%>
	</div>
	<div class="pagination"></div>
</div>

<!--今日促销-->
<div class="swiper-container">
	<div class="swiper-wrapper">
		<div class="swiper-slide">
			<div class="right-con con-active">
				<ul class="clearfix swiper-list">
                    <c:forEach items="${sessionScope.subjects4}" var="book" varStatus="vs">
					<li>
						<div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
						<div class="menu-txt">
							<h4>${book.foodname}</h4>
							<p class="list2">
								<b>￥${book.price}<em>(6折)</em></b>
							<div class="btn">
								<button class="minus">
									-
									<span  style="display: none">${book.foodname}</span>
								</button>
								<i>0</i>
								<button class="add">
									+
									<span  style="display: none">${book.foodname}</span>
								</button>
								<i class="price">${book.price}</i>
							</div>
							</p>
						</div>
					</li>
                    </c:forEach>

				</ul>
			</div>
		</div>
		<div class="swiper-slide">
			<div class="right-con con-active">
				<ul class="clearfix swiper-list">
                    <c:forEach items="${sessionScope.subjects4}" var="book" varStatus="vs">
                        <li>
                            <div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
                            <div class="menu-txt">
                                <h4>${book.foodname}</h4>
                                <p class="list2">
                                    <b>￥${book.price}<em>(6折)</em></b>
                                <div class="btn">
                                    <button class="minus">
                                        -
										<span  style="display: none">${book.foodname}</span>
                                    </button>
                                    <i>0</i>
                                    <button class="add">
                                        +
										<span  style="display: none">${book.foodname}</span>
                                    </button>
                                    <i class="price">${book.price}</i>
                                </div>
                                </p>
                            </div>
                        </li>
                    </c:forEach>

				</ul>
			</div>
		</div>

	</div>

</div>

<!--分类商品-->
<div class="main">
	<div class="left-menu">
		<ul>
			<li class="active">热销<span class="num-price"></span></li>
			<li>荤菜</li>
			<li>素菜</li>
			<li>凉菜</li>
		</ul>
	</div>
	<div class="con">
		<div class="right-con con-active" id="aa">
			<ul>
			<c:forEach items="${sessionScope.subjects}" var="book" varStatus="vs">
				<li class="clearfix">
				<div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
				<div class="menu-txt">
					<h4>${book.foodname}</h4>
					<h2>￥${book.price}</h2>

					<p class="list2">
					<div class="btn">
						<button class="minus">
							<strong>-</strong>
							<span  style="display: none">${book.foodname}</span>
						</button>
						<i>0</i>
						<button class="add">
							<strong>+</strong>
							<span  style="display: none">${book.foodname}</span>
						</button>
						<i class="price">${book.price}</i>
					</div>
					</p>
				</div>
				</li>
			</c:forEach>
			</ul>
		</div>
		<div class="right-con">
			<ul>
				<c:forEach items="${sessionScope.subjects1}" var="book" varStatus="vs">
				<li class="clearfix">
					<div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
					<div class="menu-txt">
						<h4>${book.foodname}</h4>
						<h2>￥${book.price}</h2>

						<p class="list2">
						<div class="btn">
							<button class="minus">
								<strong>-</strong>
								<span  style="display: none">${book.foodname}</span>
							</button>
							<i>0</i>
							<button class="add">
								<strong>+</strong>
								<span  style="display: none">${book.foodname}</span>
							</button>
							<i class="price">${book.price}</i>
						</div>
						</p>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="right-con">
			<ul>
				<c:forEach items="${sessionScope.subjects2}" var="book" varStatus="vs">
				<li class="clearfix">
					<div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
					<div class="menu-txt">
						<h4>${book.foodname}</h4>
						<h2>￥${book.price}</h2>

						<p class="list2">
						<div class="btn">
							<button class="minus">
								<strong>-</strong>
								<span style="display: none">${book.foodname}</span>
							</button>
							<i>0</i>
							<button class="add">
								<strong>+</strong>
								<span style="display: none">${book.foodname}</span>
							</button>
							<i class="price">${book.price}</i>
						</div>
						</p>
					</div>
				</li>
				</c:forEach>

			</ul>
		</div>
		<div class="right-con">
			<ul>
				<c:forEach items="${sessionScope.subjects3}" var="book" varStatus="vs">
				<li class="clearfix">
					<div class="menu-img"><img src="${book.foodurl}" width="55" height="55" /></div>
					<div class="menu-txt">
						<h4>${book.foodname}</h4>
						<h2>￥${book.price}</h2>

						<p class="list2">
						<div class="btn">
							<button class="minus">
								<strong>-</strong>
								<span  style="display: none">${book.foodname}</span>
							</button>
							<i>0</i>
							<button class="add">
								<strong>+</strong>
								<span  style="display: none">${book.foodname}</span>
							</button>
							<i class="price">${book.price}</i>
						</div>
						</p>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>

	</div>
</div>
<div class="footer">
	<div class="left">
		<span id="cartN">
			<img src="img/shop_03.png"/>
			<span id="totalcountshow">0</span>
			<span class="totalpriceshow">￥<em id="totalpriceshow">0</em></span>
		</span>
	</div>
	<div class="right">
		<%--<a id="btnselect" class="xhlbtn  disable" href="order-settlement.jsp">去结算</a>--%>
			<a id="btnselect" class="xhlbtn  disable" >去结算</a>
	</div>
</div>
<div style="height:1.2rem;"></div>
<nav>
	<a href="index.jsp" class="homeIcon addclasstruenav"><img src="img/index1-1.png">点餐</a>
	<%--<a href="evaluate.jsp" class="categoryIcon"><img src="img/index2.png">呼叫服务员</a>--%>
	<a href="service.jsp" class="cartIcon numberCount"><img src="img/index3.png">服务</a>
	<a href="about-us.jsp" class="userIcon"><img src="img/index4.png">我的</a>
</nav>

<script language="javascript">

    var mySwiper = new Swiper('.swiper-container',{
        prevButton:'.swiper-button-prev',
        nextButton:'.swiper-button-next',
    })
</script>
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
<!--加减和tab切换-->
<script type="text/javascript">

    $(function () {
        //加的效果
        $(".add").click(function () {
            $(this).prevAll().css("display", "inline-block");

            var fg= $(this).find("span").text();

            var n = $(this).prev().text();
            var num = parseInt(n) + 1;


            if (num == 0) { return; }
            $(this).prev().text(num);
            var danjia = $(this).next().text();//获取单价
            adddata(fg+"-"+danjia);
            var a = $("#totalpriceshow").html();//获取当前所选总价
            $("#totalpriceshow").html((a * 1 + danjia * 1).toFixed(2));//计算当前所选总价

            var nm = $("#totalcountshow").html();//获取数量
            $("#totalcountshow").html(nm*1+1);
            jss();//<span style='font-family: Arial, Helvetica, sans-serif;'></span>   改变按钮样式
        });
        //减的效果
        $(".minus").click(function () {
            var n = $(this).next().text();
            var num = parseInt(n) - 1;
            var fg= $(this).find("span").text();
            $(this).next().text(num);//减1

            var danjia = $(this).nextAll(".price").text();//获取单价
            deldata(fg+"-"+danjia);
            var a = $("#totalpriceshow").html();//获取当前所选总价
            $("#totalpriceshow").html((a * 1 - danjia * 1).toFixed(2));//计算当前所选总价

            var nm = $("#totalcountshow").html();//获取数量
            $("#totalcountshow").html(nm * 1 - 1);
            //如果数量小于或等于0则隐藏减号和数量
            if (num <= 0) {
                $(this).next().css("display", "none");
                $(this).css("display", "none");
                jss();//改变按钮样式
                return
            }
        });
        var arr = new Array();
        function adddata(gg) {
            arr.push(gg);
		}
        function deldata(gg) {
            var sfg=arr.indexOf(gg);
            if(sfg>-1)
            {
                arr.splice(sfg, 1);
			};
        }
        $("#btnselect").click(function () {
            var price=$("#totalpriceshow").html();
            arr.push(price);
            var ghh=JSON.stringify(arr);
            console.log(ghh)
           var b= arrNumber(arr);
            var a=JSON.stringify(b);
            console.log(a)
            $.ajax({
                url: "/Ordering/createording",
                type: 'post',
                data: {"data":a},
                dataType: "json",
                success: function (result) {
                        if (result.code==0){
                            window.location.href = "order-settlement.jsp"
						}else{
                            window.location.href = "index.jsp"
						}

                }

            })
              // window.location.href = "order-settlement.jsp"
        });
        function arrNumber(arr){
            var arr_number = {};
            for(var i = 0 ; i < arr.length ; i++){
                if(arr_number[arr[i]]){
                    arr_number[arr[i]]++
                }
                else{
                    arr_number[arr[i]] = 1 ;
                }
            }
            return arr_number ;
        }
        function jss() {
            var m = $("#totalcountshow").html();
            if (m > 0) {
                $(".right").find("a").removeClass("disable");
            } else {
                $(".right").find("a").addClass("disable");
            }
        };
        //选项卡
        $(".con>div").hide();
        $(".con>div:eq(0)").show();

        $(".left-menu li").click(function(){
            $(this).addClass("active").siblings().removeClass("active");
            var n = $(".left-menu li").index(this);
            $(".left-menu li").index(this);
            $(".con>div").hide();
            $(".con>div:eq("+n+")").show();
        });
    });
</script>

</body>

</html>