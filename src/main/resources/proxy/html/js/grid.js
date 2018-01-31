/**
 * layui表格扩展
 * options = {
 * 		target: "#grid",  //默认#gird，表格元素的DOM（暂只支持id，建议使用DIV），请勿在此元素类再嵌套其他的元素，因为渲染之前会被清空
 * 		url: "",	//必选，请求的接口地址
 * 		params: {},	//请求参数
 * 		columns: [	//表格栏目配置，必选
 * 			{display: "性别", name: 'sex', render: function(index, data){	//渲染函数：行号，这一行的数据 
 * 
 * 				}
 * 			}
 * 		],
 * 		usePager: true,	//是否分页，默认需要分页
 * 		page: {//可以不传，以下为默认值
 * 			cont: 'page', //分页元素的DOM，值可以传入元素id或原生DOM或jquery对象
 * 			pages: 10, //总页数
 * 			groups: 10 //连续显示分页数
 * 		}
 * }
 *
 * 请求数据返回格式固定如下(字段可以多，但不能少)：
 * {
 * 		status: 100,	//返回正常
 * 		message: "操作成功",	//返回异常是显示此提示信息
 * 		body: {
 * 			dataList: [],	//数据集
 * 			pageNum: 1, 	//当前页码
 * 			pageSize: 15, 	//当前每页的数量
 * 			pages: 20, 	//总共的页数
 * 			total: 198	//总共的记录数
 * 		}
 * }
 */
(function ($) {
    /** 分页默认配置 */
    var _default = {
        target: "#grid",
        columns: [],
        params: {},
        usePager: true,
        page: {
            cont: "page", //分页元素的DOM，值可以传入元素id或原生DOM或jquery对象
            pages: 10, //总页数
            groups: 10 //连续显示分页数
        }
    };
    /** 渲染表格 */
    $.laygrid = function (options) {
        $.laygrid.options = $.extend(_default, options);//合并配置参数
        $.laygrid.pager = {"page": 1, "pageSize": 10};
        //检测必选参数是否传入
        if (!$.laygrid.options.url || !$.laygrid.options.columns) return;
        var _options = $.laygrid.options;
        var _grid = $(_options.target);
        _grid.empty();//先清空
        var _table = $("<table id='lay-grid' class='tablelist'></table>");//生成一个table标签
        $.laygrid.grid = _table;
        var _cols = _options.columns;

        var h = "";
        for (var i = 0, len = _cols.length; i < len; i++) {
            h += "<th>" + _cols[i].display + "</th>";
        }
        _table.append("<thead><tr>" + h + "</tr></thead>");
        _grid.append(_table);
        _grid.append($("<div class='pagin' id=" + _options.page.cont + "></div>"));
        $.laygrid.loadData(_options.params);
    };

    $.laygrid.loadData = function (params) {
        var _params = $.extend($.laygrid.pager, params);
        var table = $.laygrid.grid;
        $("#lay-grid tr:not(:first)").remove();//删除非第一行的元素
        var _cols = $.laygrid.options.columns;
        $.ajax({
            url: $.laygrid.options.url,
            data: _params,
            dataType: "json",
            success: function (result) {
                if (result.status != 200) {//请求失败
                    alert(result.message);
                } else {//请求成功
                    var _options = $.laygrid.options;
                    var dataList = result.body.dataList;//获取列表结果集
                    if (!dataList) return;		//空数据直接返回
                    if (_options.usePager) {	//需要分页控件
                        var pageBar = $("#" + _options.page.cont);
                        pageBar.append("<div class='message'>共<i class='blue'>" + result.body.total + "</i>条记录，当前显示第&nbsp;<i class='blue'>" + result.body.pageNum + "&nbsp;</i>页</div>");
                        var pageIndicator = $("<ul class=\"paginList\"></ul>");
                        pageIndicator.append("<li class=\"paginItem\"><a href=\"javascript:;\"><span class='pagepre'></span></a></li>");
                        for (var pageIn = 0; pageIn< _options.page.groups; pageIn++) {
                            var li = $("<li class=\"paginItem\"></li>");
                            if(pageIn == 0) li.addClass("current");
                            li.append("<a href=\"javascript:;\">" + pageIn + "</a>");
                            pageIndicator.append(li);
                        }
                        if(result.body.pages > _options.page.groups) {
                            pageIndicator.append("<li class=\"paginItem more\"><a href='javascript:;'>...</a></li>");
                            pageIndicator.append("<li class=\"paginItem\"><a href=\"javascript:;\">" + result.body.pages + "</a></li>");
                        }

                        pageIndicator.append("<li class='paginItem'><a href=\"javascript:;\"><span class='pagenxt'></span></a></li>");
                        pageBar.append(pageIndicator);
                    }
                    //渲染数据
                    for (var index = 0; index < dataList.length; index++) {
                        var _row = $("<tr></tr>");
                        //遍历栏目
                        for (var i = 0, len = _cols.length; i < len; i++) {
                            var align = _cols[i].align ? _cols[i].align : "left";
                            if (_cols[i].render && typeof (_cols[i].render) == "function") {//优先使用自定义渲染函数
                                _row.append("<td align=" + align + ">" + _cols[i].render(index, dataList[index]) + "</td>");
                            } else {
                                _row.append("<td align=" + align + ">" + dataList[index][_cols[i].name] + "</td>");
                            }
                        }
                        table.append(_row);
                    }
                    //奇偶行背景颜色不一样
                    $(".tablelist tbody tr:odd").addClass("odd");
                }
            }
        });
    };
    $.laygrid.doPage = function (params) {

    }

})(jQuery);
