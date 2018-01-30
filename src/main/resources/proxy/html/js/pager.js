/**
 * 分页控件
 * options = {//加载分页控件
        elem: "page", //'id' 注意：这里不能加 # 号
        total: 135,  //数据总数
        page: 1,     //当前页码
        pageSize: 10,  //一页数据容量
        pages: 14, //总页数
        groups: 10, //连续显示分页数
        jump: function(obj, init){ //分页跳转回调函数

        }
    }
 */
(function ($) {
    /** 分页默认配置 */
    var _default = {
        elem: "page",
        total: 0,
        pageSize: 15,
        page: 1,
        pages: 0,
        groups: 10
    };
    /** 渲染分页控件 */
    $.pager = function (options) {
        $.pager.options = $.extend(_default, options);//合并配置参数
        var _options = $.pager.options;
        if(_options.pages < _options.groups) {
            _options.groups = _options.pages;
        }
        var pageBar = $("#" + _options.elem);
        var pageIndicator = $("<ul class=\"paginList\"></ul>");
        pageIndicator.append("<li class=\"paginItem\"><a href=\"javascript:;\"><span class='pagepre'></span></a></li>");
        for (var pageIn = 0; pageIn< _options.groups; pageIn++) {
            var li = $("<li class=\"paginItem\"></li>");
            if(pageIn == 0) li.addClass("current");
            li.append("<a href=\"javascript:;\">" + pageIn + "</a>");
            pageIndicator.append(li);
        }
        if(_options.pages > _options.groups) {
            pageIndicator.append("<li class=\"paginItem more\"><a href='javascript:;'>...</a></li>");
            pageIndicator.append("<li class=\"paginItem\"><a href=\"javascript:;\">" + _options.pages + "</a></li>");
        }

        pageIndicator.append("<li class='paginItem'><a href=\"javascript:;\"><span class='pagenxt'></span></a></li>");
        pageBar.append(pageIndicator);
    };

    /** 分页跳转 */
    $.laygrid._jump = function (params) {

    };
})(jQuery);
