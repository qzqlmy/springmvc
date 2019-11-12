<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/8 0008
  Time: 下午 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/student.css">
    <link rel="stylesheet" href="css/select2.css">
    <link rel="stylesheet" type="text/css" href="css/zxf_page.css"/>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/student.js"></script>
    <script type="text/javascript" src="js/zxf_page.js"/>
    <script src="js/ajaxfileuploa.js"></script>
    <script src="js/select2.js"></script>
    <script >
        $(document).ready(
            function () {
                $("#test").select2();
            }
        )
    </script>
    <style type="text/css">
        .search_l {
            width: 79%;
            padding-left: 1%;
        }

        .pull-left {
            float: left !important;
        }

        .form-group {
            margin-right: -15px;
            margin-left: -15px;
        }

        .search-box {
            width: 250px;
            float: left;
            font-size: 12px !important;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .pull-right {
            float: right !important;
        }

        .hw {
            height: 24px;
            width: 16px;

            margin-left: 5px;
            cursor: pointer;
        }

        .pull-right {
            float: right !important;
        }

        .btn-success {
            background-color: #1c84c6;
            border-color: #1c84c6;
            color: #FFF;
        }

        .search_r {
            width: 20%;
            margin-top: 5px;
            text-align: right;
        }

        body {

            font-size: 12px;
        }

        .s-btn {
            padding: 4px 8px;
            border-radius: 4px;
            margin: 10px 2px;
        }

        .btn-primary {
            background-color: #1ab394;
            border-color: #1ab394;
            color: #FFF;
        }

        .search-box .form-control {
            height: 26px;
            font-size: 12px !important;
            padding: 0px 2px;
        }

        .search-box select {
            width: 120px !important;
            border: 1px solid #b1c3e0;
        }

        .search-box label {
            width: 80px;
            line-height: 26px;
            float: left;
            text-align: right;
            margin-bottom: 0px;
            color: rgb(153, 153, 153);
        }

        .col-sm-8 {
            position: relative;
            min-height: 1px;
            padding-right: 15px;
            padding-left: 15px;
        }

        .search-box input, .search-box select {
            width: 120px !important;
            border-width: 1px;
            border-style: solid;
            border-color: rgb(177, 195, 224);
            border-image: initial;
        }

        .form-control, .single-line {
            background-color: rgb(255, 255, 255);
            background-image: none;
            color: inherit;
            display: block;
            width: 100%;
            font-size: 14px;
            border-width: 1px;
            border-style: solid;
            border-color: rgb(229, 230, 231);
            border-image: initial;
            border-radius: 1px;
            padding: 6px 12px;
            transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
        }
    </style>
</head>
<c:if test="${sessionScope.studentid eq null}">
    <c:redirect url="studentindex.jsp"/>
</c:if>
<body bgcolor="#fff">
<%--<p><span style="font-size:20px; color:#8B814C;right: 20rem" ><span style="margin-left: 520px">可选课程信息</span></span>--%>
<%--<p style="color: #e61e50">欢迎你：${sessionScope.studentid}</p>--%>

<%--<div class="form-group">--%>
<%--    &lt;%&ndash;@declare id="inputpassword3"&ndash;%&gt;<label for="inputPassword3" class="col-sm-2 control-label">身份证正面照片:</label>--%>
<%--    <div class="col-sm-10">--%>
<%--    <input type="hidden" name="img"  id="photoUrl"/>--%>
<%--    <input type="file"   onchange="setImg(this);">--%>
<%--    <span><img id="photourlShow" src="" width="300" height="197"/></span>--%>
<%--    </div>--%>
<%--</div>--%>

天气：${sessionScope.wether}
<div style="width: 100%;height: 50px;position:relative">

    <input id="hh" style="display: none" type="file" onchange="setImg(this);"/>
    <sspan style="position: fixed;right:7%;top: 2.5%">欢迎你,${sessionScope.studentid}</sspan>
    <img style="border-radius: 50%;position: fixed;right:3%;" id="photourlShow"
         src="http://localhost:8081/upload/imgs/20190528\1559013868085_909.png" width="30" height="30"/>

</div>


科目: <input id="subject" width="5px;!important">
课程编号: <input id="subjectnum" width="5px;!important">
年份: <input id="scoreyear" width="5px;!important">
省份：<select id="test" class="js-example-basic-multiple" name='local' multiple="true">
    <option value='1'>北京</option>
    <option value='2'>天津</option>
    <option value='3'>上海</option>
    <option value='4'>重庆</option>
    <option value='5'>河北</option>
    <option value='6'>河南</option>
    <option value='7'>山东</option>
    <option value='8'>山西</option>
    <option value='9'>安徽</option>
    <option value='10'>江西</option>
    <option value='11'>江苏</option>
    <option value='12'>浙江</option>
    <option value='13'>湖北</option>
    <option value='14'>湖南</option>
    <option value='15'>广东</option>
    <option value='16'>广西</option>
    <option value='17'>云南</option>
    <option value='18'>贵州</option>
    <option value='19'>四川</option>
    <option value='20'>陕西</option>
    <option value='21'>青海</option>
    <option value='22'>宁夏</option>
    <option value='23'>黑龙江</option>
    <option value='24'>吉林</option>
    <option value='25'>辽宁</option>
    <option value='26'>西藏</option>
    <option value='27'>新疆</option>
    <option value='28'>内蒙古</option>
    <option value='29'>海南</option>
    <option value='30'>福建</option>
    <option value='31'>甘肃</option>
    <option value='32'>港澳台</option>
</select>
<%--<form method="post"  enctype="multipart/form-data" id="form1" action="/teacher/excelUplod">--%>
<%--<table>--%>
<%--<tr>--%>
<%--<td>上传文件: </td>--%>
<%--<td> <input id="upfile" type="file" name="upfile" accept=".xls,.xlsx" /></td>--%>
<%--<td><input  type="submit" value="提交" onclick="return checkData()" /></td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</form>--%>
<div class="search_r pull-right">
    <span class="pull-right hw"></span>

    <a href="javascript:void(0);" onclick="doQuery()" class="s-btn btn-success"><i class="fa fa-search"></i>查询</a>
    <a href="javascript:void(0);" onclick="exportData()" class="s-btn btn-primary">
        <i class="fa fa-file-excel-o"></i>导出
    </a>
    <a href="javascript:void(0);" id="queryscore" class="s-btn btn-primary">
        <i class="fa fa-file-excel-o"></i>分数线查询
    </a>

</div>


<div id="fengye">
    <table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;"
           id="">
        <tr style="background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;">
            <td width="70px">id</td>
            <td>课程编号</td>
            <td>科目</td>
            <td>上课地点</td>
            <td>上课时间</td>
            <td>课时</td>
        </tr>
        <c:forEach items="${sessionScope.subjects}" var="subject" varStatus="vs">
            <tr style="background: #e5efeb; text-align: center;height: 2.2rem;">
                <td>${vs.count}</td>
                <td>${subject.subjectid}</td>
                <td>${subject.subject}</td>
                <td>${subject.classplace}</td>
                <td>${subject.classtime}</td>
                <td>${subject.classhour}</td>

            </tr>
        </c:forEach>

    </table>
</div>
<div class="zxf_pagediv"></div>
<div class="zxf_pagedi"></div>


<script type="text/javascript">
    $("#photourlShow").click(function () {
        $("#hh").click();

    })

    //JS校验form表单信息
    function checkData() {
        var fileDir = $("#upfile").val();
        var suffix = fileDir.substr(fileDir.lastIndexOf("."));
        if ("" == fileDir) {
            alert("选择需要导入的Excel文件！");
            return false;
        }
        if (".xls" != suffix && ".xlsx" != suffix) {
            alert("选择Excel格式的文件导入！");
            return false;
        }
        return true;
    }

    //ajax提交excel
    $("#btn").click(function () {
        if (checkData()) {
            $.ajaxFileUpload({
                url: "/teacher/ajaxUpload.do",
                type: "POST",
                dataType: "text",
                fileElementId: "upfile",
                success: function (data) {
                    //alert(data);
                    console.log(data);
                    $("#upfile").val("");
                },
                error: function (erro) {
                    console.log(erro);
                }
            });
        }
    });


    doQuerycount();

    function exportData() {

        window.location.href = '/teacher/export'
    }

    $("#queryscore").click(function () {
        var subject = $('#subject').val();
        var scoreyear = $('#scoreyear').val();
        var options = $("#test option:selected").text();
        var sf = $("#test").val();
        alert(sf);
        $.ajax({
            url: "/teacher/queryscore",
            type: 'post',
            data: {"scoreyear": scoreyear, "provenice": sf},
            dataType: "json",
            success: function (result) {
                var len = result.length;
                table = '<table border="1" width="100%" style=" border-collapse:collapse;border-spacing:0; border:1px  solid  #FFFFFF;">';
                for (var i = 0; i < len; i++) {
                    if (i == 0) {

                        table += "<tr  style=\"background:#50aca2;color: #fff;border-bottom-width: 0;  text-align: center; height: 2.2rem;\">\n" +
                            "        <td>年份</td>\n" +
                            "        <td>考生所在地</td>\n" +
                            "        <td>考生类别</td>\n" +
                            "        <td>批次</td>\n" +
                            "        <td>最低控制分数线</td>\n" +
                            "    </tr><tbody>";

                    }
                    // table +="<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                    //     "<td>"+result[i].id+"</td>"+"<td>"+result[i].subjectid+"</td>"+"<td>"+result[i].subject+"</td>"+"<td>"+result[i].classplace+"</td>"+"<td>"+result[i].classtime+"</td>"+"<td>"+result[i].classhour+"</td>"
                    // "</tr>";
                    table += "<tr style=\"background: #e5efeb; text-align: center;height: 2.2rem;\">" +
                        "<td>" + result[i].year + "</td>" + "<td>" + result[i].provice + "</td>" + "<td>" + result[i].subject + "</td>" + "<td>" + result[i].pici + "</td>" + "<td>" + result[i].score + "</td>"
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

    function doQuery() {
        var subject = $('#subject').val();
        var subjectnum = $('#subjectnum').val();

        $.ajax({
            url: "/teacher/getSubject",
            type: 'post',
            data: {"subject": subject, "subjectnum": subjectnum, "id": 1},
            dataType: "json",
            success: function (result) {
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
                $("#fengye").html(table);
                if (subject == "" && subjectnum == "") {


                } else {
                    var count = ''
                    $.ajax({
                        url: "/teacher/getSubjectcoun",
                        type: 'post',
                        data: {"subject": subject, "subjectnum": subjectnum, "id": 1},
                        dataType: "json",
                        success: function (result) {
                            if (result.count < 10) {
                                count = 1;
                            } else {
                                count = Math.round(result.count / 10);
                            }

                            //翻页
                            $(".zxf_pagediv").html("");
                            $(".zxf_pagediv").empty()
                            /*上一页*/
                            if (count > 1) {
                                $(".zxf_pagediv").remove('.prebtn');
                                $(".zxf_pagediv").append('<a href="javascript:;" class="prebtn">上一页</a>');
                            } else {
                                $(".zxf_pagediv").remove('.prevPage');
                                $(".zxf_pagediv").append('<span class="disabled">上一页</span>');
                            }
                            var start = 1, end = 9;
                            for (; start <= end; start++) {
                                if (start <= count && start >= 1) {
                                    if (start == count - 1) {
                                        $(".zxf_pagediv").append('<span class="current">' + start + '</span>');
                                    } else if (start == 1) {
                                        $(".zxf_pagediv").append('<a href="javascript:;" class="zxfPagenum nextpage">' + start + '</a>');
                                    } else {

                                        $(".zxf_pagediv").append('<a href="javascript:;" class="zxfPagenum">' + start + '</a>');
                                    }
                                }
                            }
                            if (end < count) {
                                $(".zxf_pagediv").append('<span>...</span>');
                            }
                            /*下一页*/

                            var subject = $('#subject').val();
                            var subjectnum = $('#subjectnum').val();
                            if (subject == "" && subjectnum == "") {
                                $(".zxf_pagediv").remove('.nextbtn');
                                $(".zxf_pagediv").append('<a href="javascript:;" class="nextbtn">下一页</a>');
                            } else {
                                alert(subjectnum)
                                $(".zxf_pagediv").remove('.nextbtn');
                                $(".zxf_pagediv").remove('.nextbt');
                                $(".zxf_pagediv").append('<a href="javascript:;" class="nextbt">下一页</a>');
                            }


                            /*尾部*/
                            $(".zxf_pagediv").append('<span>' + '共' + '<b>' + count + '</b>' + '页，' + '</span>');
                            // $(".zxf_pagediv").createPage({
                            //     pageNum: count,
                            //     current: 9,
                            //     backfun: function (e) {
                            //         //console.log(e);//回调
                            //     }
                            // });
                            $("#photourlShow").attr("src", result.url);


                        },
                        error: function (error) {
                            alert('页面加载异常!' + error);
                        }
                    });

                }


            },
            error: function (error) {
                alert('页面加载异常!' + error);
            }
        });

    }

    function doQuerycount() {
        var count = ''
        $.ajax({
            url: "/teacher/getSubjectcount",
            type: 'post',
            dataType: "json",
            success: function (result) {

                count = result.count / 10;
                //翻页

                $(".zxf_pagediv").createPage({
                    pageNum: count,
                    current: 9,
                    backfun: function (e) {
                        //console.log(e);//回调
                    }
                });
                $("#photourlShow").attr("src", result.url);


            },
            error: function (error) {
                alert('页面加载异常!' + error);
            }
        });

    }

    // $.ajax({
    //     url:"/teacher/getHeadPortrait",
    //     type: 'post',
    //     dataType: "json",
    //     success: function(result) {
    //         if(result.code==0){
    //             $("#photourlShow").attr("src", result.url);
    //         }else{
    //             $("#photourlShow").attr("src","http://localhost:8081/upload/imgs/20190528\\1559013868085_909.png");
    //         }
    //
    //
    //
    //     },
    //     error: function (error) {
    //         alert('页面加载异常!' + error);
    //     }
    // });
    function setImg(obj) {
        var f = $(obj).val();

        console.log(obj);
        if (f == null || f == undefined || f == '') {
            return false;
        }
        if (!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
            alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
            $(obj).val('');
            return false;
        }
        var data = new FormData();
        console.log(data);
        $.each($(obj)[0].files, function (i, file) {
            data.append('file', file);
            console.log(file);
        });
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/Uploader/uploadImg",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            dataType: "json",
            success: function (ret) {
                console.log(ret);
                if (ret.code == 0) {
                    $("#photoUrl").val(ret.result.url);//将地址存储好
                    $("#photourlShow").attr("src", ret.result.url);//显示图片
                    // alertOk(ret.message);
                    $("#hh").hide();
                } else {
                    alertError(ret.message);
                    $("#url").val("");
                    $(obj).val('');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
            }
        });

    }
</script>
</body>
</html>
