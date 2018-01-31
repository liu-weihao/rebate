var contextPath = "http://127.0.0.1";
/** 系统出现异常 */
var ERROR = 500;
/** 请求超时 */
var TIMEOUT = 408;
/** 找不到页面 */
var NOT_FOUND = 404;
/** 无效的请求 */
var BAD_REQUEST = 400;
/** 登录过期 */
var LOGIN_EXPIRE = 401;
/** 无权限访问 */
var NO_AUTHORITY = 405;
/** 必填的参数没有传入 */
var PARAM_BLANK = 300;
/** 用户未登录，访问了需要登录的api */
var NOT_LOGIN = 301;
/** 业务处理未满足条件，导致失败 */
var FAILED = 100;
/** 正常情况 */
var SUCCESS = 200;

$.ajaxSetup({
    error : function(jqXHR, textStatus, errorThrown) {
        switch (jqXHR.status) {
            case (ERROR):
                alert("服务器系统内部错误");
                break;
            case (BAD_REQUEST):
                alert("无效的请求");
                break;
            case (LOGIN_EXPIRE):
                alert("登录过期，请重新登录");
                window.top.location = contextPath + "/login.html";
                break;
            case (NO_AUTHORITY):
                alert("无权限执行此操作");
                break;
            case (NOT_FOUND):
                alert("您请求的页面未找到");
                break;
            case (TIMEOUT):
                alert("请求超时");
                break;
            default:
                break;
        }
    }
});


/**
 * JS中获取URL地址根路径
 * @returns {String}
 */
function getBasePath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);    //获取主机地址，如： http://localhost:8083
    var localhostPath = curWwwPath.substring(0, pos);    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName + '/';
}

function createGrid(options) {

    var _default = {
        height: "680px",
        gridManagerName: 'grid_manager',
        supportRemind: false,
        disableCache: false,
        supportAjaxPage: true,
        supportSorting: false,
        supportDrag: false,
        supportAutoOrder: true,
        ajax_type: 'GET',
        dataKey: 'dataList',
        totalsKey: 'total',
        pageSize: 20
    };
    var _options = $.extend(_default, options);
    return $("#" + _options.elem).GM(_options);
}


/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "D+": this.getDate(), //日
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/([Y,y]+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

/**
 * 日期截取
 */
function dateFormatYMD(data) {
    if (!data || data == "") {
        return "";
    } else {
        return data.substring(0, 10);
    }
}

(function ($) {
    /**
     * 将form表单序列化成一个JSON对象
     */
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };

    $.fn.grid = function(options) {
        var _default = {
            gridManagerName: 'grid_manager',
            supportRemind: false,
            disableCache: false,
            height: '300px',
            supportAjaxPage: true,
            supportSorting: false,
            supportDrag: false,
            supportAutoOrder: true,
            ajax_type: 'GET',
            dataKey: 'dataList',
            totalsKey: 'total',
            pageSize: 20
        };
        var _options = $.extend(_default, options);
        return this.GM(_options);
    }
})(jQuery);