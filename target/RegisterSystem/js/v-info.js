/*
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: 鱼小陌
 * @Date: 2019-09-04 19:25:07
 * @LastEditors: 鱼小陌
 * @LastEditTime: 2019-09-05 20:58:44
 */

$(function(){
    if($.getUrlParam('type')!="admin"&&$.getUrlParam('type')!="agent"){
        return window.location.href = "./login.html";
    }
    // 菜单栏
    $('#btn-menu').on('click', function() {
		$('body').toggleClass('v-active');
    });
    // 工具提示
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });

    // 页面
    var page =  $("#page");
   
    // dataAddress
    var dataAddress = 'user-info';

    // 按钮事件
    var userInfo = $('#user-info');
    var agents = $('#agents');
    var users = $('#users');

    // 退出登录
    $("#exit").click(function(){
        $.getJSON("exit",function (res) {
                window.location.href = './login.html';
        });
    });


    userInfo.click(function (e) { 
        e.preventDefault();
        var data = page.data('user-info');
        dataAddress='user-info';
        if( data!==undefined){
            page.html(data);
        }else {
            var href = './views/agent.templet';
            if(window.location.href.indexOf('agent')===-1){
                href = './views/admin.templet';
            }
            page.load(href);
        }
        userInfo.addClass('nav-active');
        agents.removeClass('nav-active');
        users.removeClass('nav-active');

    }).trigger('click');

    
    // 隐藏选择
    if(window.location.href.indexOf('agent')!==-1){
        agents.remove().parent().remove();
    }
    agents.click(function (e) {
        e.preventDefault();
        var data = page.data('agents');
        dataAddress='agents';
        if( data!==undefined){
            page.html(data);
        }else {
            page.load("./views/agents.templet");
        }
        userInfo.removeClass('nav-active');
        agents.addClass('nav-active');
        users.removeClass('nav-active');
    });

    
    users.click(function (e) { 
        e.preventDefault();
        var data = page.data('users');
        dataAddress='users';
        if( data!==undefined){
            page.html(data);
        }else {
            page.load("./views/users.templet");
        }
        userInfo.removeClass('nav-active');
        agents.removeClass('nav-active');
        users.addClass('nav-active');
    });
    
    // 刷新事件
    $('#refresh').click(function(){
        // 移除
        page.removeData(dataAddress);
        // 刷新
        $("#"+dataAddress).trigger('click');        
    })
    
});
//替换table数据和worksheet名字
var format = function (s, c) {
    return s.replace(/{(\w+)}/g,
        function (m, p) {
            return c[p];
        });
};
//base64转码
var base64 = function (s) {
    return window.btoa(unescape(encodeURIComponent(s)));
};
function downloadExcel(tableid, sheetName) {
    var uri = 'data:application/vnd.ms-excel;base64,';
    var template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel"'
            + 'xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>'
            + '<x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets>'
            + '</x:ExcelWorkbook></xml><![endif]-->'
            +'</head><body><table>{table}</table></body></html>';
    if (!tableid.nodeType) tableid = document.getElementById(tableid);
    var ctx = {worksheet: sheetName || 'Worksheet', table: tableid.innerHTML};
    window.location.href = uri + base64(format(template, ctx));
}
