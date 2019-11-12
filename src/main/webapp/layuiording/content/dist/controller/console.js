/** layuiAdmin-v1.0.0-beta6 LPPL License By http://www.layui.com/admin/ */
;layui.define(function (e) {
    layui.use(["admin", "carousel"], function () {
        var e = layui.$, t = (layui.admin, layui.carousel), a = layui.element, i = layui.device();
        e(".layadmin-carousel").each(function () {
            var a = e(this);
            t.render({
                elem: this,
                width: "100%",
                arrow: "none",
                interval: a.data("interval"),
                autoplay: a.data("autoplay") === !0,
                trigger: i.ios || i.android ? "click" : "hover",
                anim: a.data("anim")
            })
        }), a.render("progress")
    }), layui.use(["carousel", "echarts"], function () {

        var arr1 = new Array();
        var arr2 = new Array();
        var arr3 = new Array();
        $.ajax({
            url: "/Ordering/weeklist",
            type: 'post',
            dataType: "json",
            success: function (result) {

                var len = result.length;
                for (var i = 0; i < len; i++) {
                    var str = result[i].listdate.replace(/-/g,"").substr(4, 7);
                    // var aa=result[i].listprice.replace(/ /g,"0");
                    var aa ="";
                    if(result[i].listprice=="undefined"){
                        aa==0;
                    }else{
                        aa=result[i].listprice;
                    }
                    arr1.push(str)
                    arr2.push(result[i].listcount)
                    arr3.push(aa)

                }

            }
        });
        var e = layui.$, t = layui.carousel, a = layui.echarts, i = [], l = [{
            title: {text: "今日流量趋势", x: "center", textStyle: {fontSize: 14}},
            tooltip: {trigger: "axis"},
            legend: {data: ["", ""]},
            xAxis: [{
                type: "category",
                boundaryGap: !1,
                data: arr1
            }],
            yAxis: [{type: "value"}],
            series: [{
                name: "PV",
                type: "line",
                smooth: !0,
                itemStyle: {normal: {areaStyle: {type: "default"}}},
                data: arr2
            }

            ]
        },

            {
            title: {text: "最近一周新增的用户量", x: "center", textStyle: {fontSize: 14}},
            tooltip: {trigger: "axis", formatter: "{b}<br>新增用户：{c}"},
            xAxis: [{type: "category", data: arr1}],
            yAxis: [{type: "value"}],
            series: [{type: "line", data: arr3}]
        }], n = e("#LAY-index-dataview").children("div"), r = function (e) {
            i[e] = a.init(n[e], layui.echartsTheme), i[e].setOption(l[e]), window.onresize = i[e].resize
        };
        if (n[0]) {
            r(0);
            var o = 0;
            t.on("change(LAY-index-dataview)", function (e) {
                r(o = e.index)
            }), layui.admin.on("side", function () {
                setTimeout(function () {
                    r(o)
                }, 300)
            }), layui.admin.on("hash(tab)", function () {
                layui.router().path.join("") || r(o)
            })
        }
    }), layui.use("table", function () {
        var e = (layui.$, layui.table);
        e.render({
            elem: "#LAY-index-topSearch",
            url: "./content/json/console/top-search.js",
            page: !0,
            cols: [[{type: "numbers", fixed: "left"}, {
                field: "keywords",
                title: "关键词",
                minWidth: 300,
                templet: '<div><a href="https://www.baidu.com/s?wd={{ d.keywords }}" target="_blank" class="layui-table-link">{{ d.keywords }}</div>'
            }, {field: "frequency", title: "搜索次数", minWidth: 120, sort: !0}, {
                field: "userNums",
                title: "用户数",
                sort: !0
            }]],
            skin: "line"
        }), e.render({
            elem: "#LAY-index-topCard",
            url: "./content/json/console/top-card.js",
            page: !0,
            cellMinWidth: 120,
            cols: [[{type: "numbers", fixed: "left"}, {
                field: "title",
                title: "标题",
                minWidth: 300,
                templet: '<div><a href="{{ d.href }}" target="_blank" class="layui-table-link">{{ d.title }}</div>'
            }, {field: "username", title: "发帖者"}, {field: "channel", title: "类别"}, {
                field: "crt",
                title: "点击率",
                sort: !0
            }]],
            skin: "line"
        })
    }), e("console", {})
});