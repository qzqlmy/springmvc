<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

	<head>
		<meta charset="utf-8" />
		<title>新增收货地址</title>
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
		<style>
			.formarea li a{line-height: 0.94rem;color: #989898;}
			textarea{border: none;width:100%;display: block;margin: auto;}
			.formarea li:last-child{height:2rem;padding: 3%;}
			.picker .picker-panel .picker-choose .picker-title{font-size: .3rem;}
			.picker .picker-panel .wheel-wrapper .wheel .wheel-scroll .wheel-item{font-size: .3rem;}
		</style>
	</head>

	<body style="width: 100%;overflow-x: hidden;">
		
		<ul class="formarea">
			<li>
				<label class="lit">姓名</label>
				<input type="text" placeholder="请输入您的姓名" class="textbox" />
			</li>
			<li>
				<label class="lit">手机号</label>
				<input type="number" placeholder="请输入您的手机号" class="textbox" />
			</li>
			<li>
				<label class="lit">所在地区</label>
				<a href="#" class="btn btn-info btn-lg active textbox" role="button" id="sel_city">请选择</a>
			</li>
			<li>
				<textarea name="" rows="" cols="" placeholder="请填写详细地址"></textarea>				
			</li>
		</ul>
		<div style="height:1.2rem;"></div>
		<div class="button-order-det">确认</div>
		
		<script src="js/picker.min.js"></script>
		<script src="js/city.js"></script>
		<script>
		var nameEl = document.getElementById('sel_city');
		
		var first = []; /* 省，直辖市 */
		var second = []; /* 市 */
		var third = []; /* 镇 */
		
		var selectedIndex = [0, 0, 0]; /* 默认选中的地区 */
		
		var checked = [0, 0, 0]; /* 已选选项 */
		
		function creatList(obj, list){
		  obj.forEach(function(item, index, arr){
		  var temp = new Object();
		  temp.text = item.name;
		  temp.value = index;
		  list.push(temp);
		  })
		}
		
		creatList(city, first);
		
		if (city[selectedIndex[0]].hasOwnProperty('sub')) {
		  creatList(city[selectedIndex[0]].sub, second);
		} else {
		  second = [{text: '', value: 0}];
		}
		
		if (city[selectedIndex[0]].sub[selectedIndex[1]].hasOwnProperty('sub')) {
		  creatList(city[selectedIndex[0]].sub[selectedIndex[1]].sub, third);
		} else {
		  third = [{text: '', value: 0}];
		}
		
		var picker = new Picker({
		    data: [first, second, third],
		  selectedIndex: selectedIndex,
		    title: '地址选择'
		});
		
		picker.on('picker.select', function (selectedVal, selectedIndex) {
		  var text1 = first[selectedIndex[0]].text;
		  var text2 = second[selectedIndex[1]].text;
		  var text3 = third[selectedIndex[2]] ? third[selectedIndex[2]].text : '';
		
		    nameEl.innerText = text1 + ' ' + text2 + ' ' + text3;
		});
		
		picker.on('picker.change', function (index, selectedIndex) {
		  if (index === 0){
		    firstChange();
		  } else if (index === 1) {
		    secondChange();
		  }
		
		  function firstChange() {
		    second = [];
		    third = [];
		    checked[0] = selectedIndex;
		    var firstCity = city[selectedIndex];
		    if (firstCity.hasOwnProperty('sub')) {
		      creatList(firstCity.sub, second);
		
		      var secondCity = city[selectedIndex].sub[0]
		      if (secondCity.hasOwnProperty('sub')) {
		        creatList(secondCity.sub, third);
		      } else {
		        third = [{text: '', value: 0}];
		        checked[2] = 0;
		      }
		    } else {
		      second = [{text: '', value: 0}];
		      third = [{text: '', value: 0}];
		      checked[1] = 0;
		      checked[2] = 0;
		    }
		
		    picker.refillColumn(1, second);
		    picker.refillColumn(2, third);
		    picker.scrollColumn(1, 0)
		    picker.scrollColumn(2, 0)
		  }
		
		  function secondChange() {
		    third = [];
		    checked[1] = selectedIndex;
		    var first_index = checked[0];
		    if (city[first_index].sub[selectedIndex].hasOwnProperty('sub')) {
		      var secondCity = city[first_index].sub[selectedIndex];
		      creatList(secondCity.sub, third);
		      picker.refillColumn(2, third);
		      picker.scrollColumn(2, 0)
		    } else {
		      third = [{text: '', value: 0}];
		      checked[2] = 0;
		      picker.refillColumn(2, third);
		      picker.scrollColumn(2, 0)
		    }
		  }
		
		});
		
		picker.on('picker.valuechange', function (selectedVal, selectedIndex) {
		  console.log(selectedVal);
		  console.log(selectedIndex);
		});
		
		nameEl.addEventListener('click', function () {
		    picker.show();
		});
		</script>
	</body>

</html>