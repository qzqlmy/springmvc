 {
    "code": 0,
	"msg": "",
	"data": [{
		"title": "主页"
		,"icon": "layui-icon-home"
		,"list": [{
			"title": "控制台"
			,"jump": "/"
		}]
	},

	{
		"name": "photoManagement",
		"title": "商品管理",
		"icon": "layui-icon-picture-fine",
		"list": [{
			"name": "posterManagement"
			,"title": "商品查询"
		},{
            "name": "add"
            ,"title": "商品新增"

        }]
	},
    {
        "name": "photoManagement",
        "title": "订单管理",
        "icon": "layui-icon-picture-fine",
        "list": [{
            "name": "ording"
            ,"title": "订单查询"
        }]
    },


	{
		"name": "personalCenter",
		"title": "个人中心",
		"icon": "layui-icon-user",
		"list": [{
			"name": "personalInformation"
			,"title": "个人信息"
		},{
			"name": "myMessage"
			,"title": "我的消息"
		}]
	}, {
		"name": "other",
		"title": "其它",
		"icon": "layui-icon-set",
		"list": [{
			"name": "register"
			,"title": "注册"
			,"jump": "other/register"
		},{
			"name": "login"
			,"title": "登录"
            ,"jump": "other/login"
		},{
			"name": "daily"
			,"title": "日志管理"
            ,"jump": "other/daily"
		},{
			"name": "systemSet"
			,"title": "系统设置"
            ,"jump": "other/systemSet"
		}]
	}]
}