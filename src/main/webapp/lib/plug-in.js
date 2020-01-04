/*
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: 鱼小陌
 * @Date: 2019-09-04 19:25:07
 * @LastEditors: 鱼小陌
 * @LastEditTime: 2019-09-04 19:25:07
 */

// 通过参数名获取url参数值
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);