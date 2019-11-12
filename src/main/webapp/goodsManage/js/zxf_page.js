(function($){
	var zp = {
		init:function(obj,pageinit){
			return (function(){
				zp.addhtml(obj,pageinit);
				zp.bindEvent(obj,pageinit);
			}());
		},
		addhtml:function(obj,pageinit){
            obj.remove('.nextbtn');
			return (function(){
				obj.empty();
				/*上一页*/
				if (pageinit.current > 1) {
                    obj.remove('.prebtn');
					obj.append('<a href="javascript:;" class="prebtn">上一页</a>');
				} else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">上一页</span>');
				}
				/*中间页*/
				if (pageinit.current >4 && pageinit.pageNum > 4) {
					obj.append('<a href="javascript:;" class="zxfPagenum">'+1+'</a>');
					obj.append('<a href="javascript:;" class="zxfPagenum">'+2+'</a>');
					obj.append('<span>...</span>');
				}
				if (pageinit.current >4 && pageinit.current <= pageinit.pageNum-5) {
					var start  = pageinit.current - 2,end = pageinit.current + 2;
				}else if(pageinit.current >4 && pageinit.current > pageinit.pageNum-5){
					var start  = pageinit.pageNum - 4,end = pageinit.pageNum;
				}else{
					var start = 1,end = 9;
				}
				for (;start <= end;start++) {
					if (start <= pageinit.pageNum && start >=1) {
						if (start == pageinit.current) {
							obj.append('<span class="current">'+ start +'</span>');
						} else if(start == pageinit.current+1){
							obj.append('<a href="javascript:;" class="zxfPagenum nextpage">'+ start +'</a>');
						}else{

							obj.append('<a href="javascript:;" class="zxfPagenum">'+ start +'</a>');
						}
					}
				}
				if (end < pageinit.pageNum) {
					obj.append('<span>...</span>');
				}
				/*下一页*/
				if (pageinit.current >= pageinit.pageNum) {
					obj.remove('.nextbtn');
					obj.append('<span class="disabled">下一页</span>');
				} else{
                    var subject=$('#subject').val();
                    var subjectnum=$('#subjectnum').val();
                    if(subject=="" && subjectnum=="") {
                    obj.remove('.nextbtn');
                        obj.append('<a href="javascript:;" class="nextbtn">下一页</a>');
					 }else{
                    	alert(subjectnum)
                        obj.remove('.nextbtn');
                        obj.remove('.nextbt');
                        obj.append('<a href="javascript:;" class="nextbt">下一页</a>');
					}

				 }
				/*尾部*/
				obj.append('<span>'+'共'+'<b>'+pageinit.pageNum+'</b>'+'页，'+'</span>');
				obj.append('<span>'+'到第'+'<input type="number" class="zxfinput" />'+'页'+'</span>');
				obj.append('<span class="zxfokbtn">'+'确定'+'</span>');
			}());
		},
		bindEvent:function(obj,pageinit){
			return (function(){
				obj.on("click","a.prebtn",function(){
					var cur = parseInt(obj.children("span.current").text());
					var current = $.extend(pageinit, {"current":cur-1});
                    var subject=$('#subject').val();
                    var subjectnum=$('#subjectnum').val();
					var bb=cur-1;
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
                    if(subject=="" && subjectnum==""){
                        $.ajax({
                            url:"/teacher/queryBypage",
                            type: 'post',
                            data:{"id":bb},
                            dataType: "json",
                            success: function(result) {
                                var b = JSON.stringify(result);
                                var len = result .length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for(var i = 0; i < len; i++) {
                                    if(i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html(table);


                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        })
                    }else{
                        $.ajax({
                            url:"/teacher/getSubject",
                            type: 'post',
                            data:{"subject":subject,"subjectnum":subjectnum,"id":bb},
                            dataType: "json",
                            success: function(result) {
                                var len = result .length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for(var i = 0; i < len; i++) {
                                    if(i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html(table);
                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        });
                    }


				});
				obj.on("click","a.zxfPagenum",function(){
					var cur = parseInt($(this).text());
                    var subject=$('#subject').val();
                    var subjectnum=$('#subjectnum').val();
                    if(subject=="" && subjectnum==""){
                        $.ajax({
                            url:"/teacher/queryBypage",
                            type: 'post',
                            data:{"id":cur},
                            dataType: "json",
                            success: function(result) {
                                var b = JSON.stringify(result);
                                var len = result .length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for(var i = 0; i < len; i++) {
                                    if(i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html(table);


                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        });
                    }else{
                        $.ajax({
                            url:"/teacher/getSubject",
                            type: 'post',
                            data:{"subject":subject,"subjectnum":subjectnum,"id":cur},
                            dataType: "json",
                            success: function(result) {
                                var len = result .length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for(var i = 0; i < len; i++) {
                                    if(i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html(table);
                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        });
                    }

                   // alert(cur)
					var current = $.extend(pageinit, {"current":cur});
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
				});
                obj.on("click","a.nextbt",function(){
                    var cur = parseInt(obj.children("span.current").text());
                    var current = $.extend(pageinit, {"current":cur+1});
                    var subject=$('#subject').val();
                    var subjectnum=$('#subjectnum').val();
                    var aa=cur+1;
                    zp.addhtml(obj,current);
                    if (typeof(pageinit.backfun)=="function") {
                        pageinit.backfun(current);
                    }

                        alert(22222)
                        $.ajax({
                            url:"/teacher/getSubject",
                            type: 'post',
                            data:{"subject":subject,"subjectnum":subjectnum,"id":aa},
                            dataType: "json",
                            success: function(result) {
                                var len = result .length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for(var i = 0; i < len; i++) {
                                    if(i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html(table);
                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        });


                });
				obj.on("click","a.nextbtn",function(){
					var cur = parseInt(obj.children("span.current").text());
					var current = $.extend(pageinit, {"current":cur+1});

					var aa=cur+1;
					zp.addhtml(obj,current);
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}


						$.ajax({
                            url: "/teacher/queryBypage",
                            type: 'post',
                            data: {"id": aa},
                            dataType: "json",
                            success: function (result) {
                                var b = JSON.stringify(result);
                                var len = result.length;
                                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                                for (var i = 0; i < len; i++) {
                                    if (i == 0) {

                                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                            "        <td width=\"70px\">id</td>\n" +
                                            "        <td>课程编号</td>\n" +
                                            "        <td>科目</td>\n" +
                                            "        <td>上课地点</td>\n" +
                                            "        <td>上课时间</td>\n" +
                                            "        <td>课时</td>\n" +
                                            "    </tr><tbody>";

                                    }
                                    table += "<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                        "<td>" + result[i].id + "</td>" + "<td>" + result[i].subjectid + "</td>" + "<td>" + result[i].subject + "</td>" + "<td>" + result[i].classplace + "</td>" + "<td>" + result[i].classtime + "</td>" + "<td>" + result[i].classhour + "</td>"
                                    "</tr>";

                                }
                                table += "</tbody></table>";
                                $("#fengye").html("");
                                $("#fengye").html(table);


                            },
                            error: function (error) {
                                alert('页面加载异常!' + error);
                            }
                        });

				});

				obj.on("click","span.zxfokbtn",function(){
					var cur = parseInt($("input.zxfinput").val());
					var current = $.extend(pageinit, {"current":cur});
					zp.addhtml(obj,{"current":cur,"pageNum":pageinit.pageNum});
					if (typeof(pageinit.backfun)=="function") {
						pageinit.backfun(current);
					}
                    $.ajax({
                        url:"/teacher/queryBypage",
                        type: 'post',
                        data:{"id":cur},
                        dataType: "json",
                        success: function(result) {
                            var b = JSON.stringify(result);
                            var len = result .length;
                            table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                            for(var i = 0; i < len; i++) {
                                if(i == 0) {

                                    table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                                        "        <td width=\"70px\">id</td>\n" +
                                        "        <td>课程编号</td>\n" +
                                        "        <td>科目</td>\n" +
                                        "        <td>上课地点</td>\n" +
                                        "        <td>上课时间</td>\n" +
                                        "        <td>课时</td>\n" +
                                        "    </tr><tbody>";

                                }
                                table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                                    "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                                "</tr>";

                            }
                            table += "</tbody></table>";
                            $("#fengye").html(table);


                        },
                        error: function (error) {
                            alert('页面加载异常!' + error);
                        }
                    });

				});
			}());
		}
	}
	$.fn.createPage = function(options){
		var pageinit = $.extend({
			pageNum : 15,
			current : 1,
			backfun : function(){}
		},options);
		zp.init(this,pageinit);
	}
}(jQuery));
