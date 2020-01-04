/*
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: 鱼小陌
 * @Date: 2019-09-04 19:25:07
 * @LastEditors: 鱼小陌
 * @LastEditTime: 2019-09-05 20:58:29
 */

$(function(){
    // 处理管理员切换
    var $type = $('#type');
    $('#type').val('agent');
    $('#username').parent().addClass('hidden');
    $type.change(function(){
        $('#userphone').parent().toggleClass('hidden');
        $('#username').parent().toggleClass('hidden');
        $('input').val('').parent().removeClass('has-error').removeClass('has-success');
        $('#codeimg').trigger('click');
    });
    // 验证码
    var codeImgUrl ='./getImg?t=';
    $('#codeimg').click(function(){
        this.src = codeImgUrl+new Date().getTime();
    }).attr('src',codeImgUrl+new Date().getTime());
    // alert不删除隐藏起来
    $("div.alert .close").click(function(e){
        $(this).parent().removeClass('show').addClass("hidden");
    });
    var $phone = $('#userphone');
    var $name = $('#username');
    var $password = $('#password');
    var $code = $('#code');
    var $sub = $('#sub');
    // 提交表单
    $sub.click(function(e){
        e.preventDefault();

        // 提交开关
        var flag = false;
        //消息显示框
        var $alert = $('#alertResult');

        // 验证验证码
        var reg = new RegExp(/^[A-Za-z0-9]{5}$/);
        if(reg.test($code.val())){
            $code.parent().removeClass('has-error').addClass('has-success');
            flag = true;
        }else {
            $code.parent().removeClass('has-success').addClass('has-error');
            flag = false;
        }
        var data = {};
        if($type.val()==="agent"){
            // 验证手机号
            reg = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
            if(reg.test($phone.val())){
                $phone.parent().removeClass('has-error').addClass('has-success');
                flag = true;
            }else {
                $phone.parent().removeClass('has-success').addClass('has-error');
                flag = false;
            }
            data = {
                type:$type.val(),
                phone:$phone.val(),
                pass:$password.val(),
                code:$code.val()
            }
        }else {
            // 验证用户名
            reg = /^[A-Za-z0-9]{1,10}$/;
            if(reg.test($name.val())){
                $name.parent().removeClass('has-error').addClass('has-success');
                flag = true;
            }else {
                $name.parent().removeClass('has-success').addClass('has-error');
                flag = false;
            }
            data = {
                type:$type.val(),
                name:$name.val(),
                pass:$password.val(),
                code:$code.val()
            }
        }
        // 验证密码
        if($password.val().length<1){
            $password.parent().removeClass('has-success').addClass('has-error');
            flag = false;
        }

        if(flag){
            $sub.button('loading');
            $.ajax({
                type: "POST",  //默认get
                url: 'Login',  //默认当前页
                data: data,  //格式{key:value}
                dataType: "json",
                success: function (res) {  //请求成功回调
                    $alert.removeClass("hidden");
                    if(res.status==200){
                        // 显示信息
                        $alert.removeClass('alert-warning').addClass('alert-success');
                        // 跳转url
                        window.location.href = "./"+res.data;
                    }else {
                        $('#codeimg').trigger("click")
                        $alert.removeClass('alert-success').addClass('alert-warning');
                    }
                    $alert.find('strong').text(res.msg);
                },
                error: function (e) {  //请求超时回调
                    $alert.removeClass('alert-warning').removeClass("hidden").addClass('alert-success');
                    if(e.statusText === "timeout")e.statusText = "请求超时";
                    $alert.find('strong').text(e.statusText);
                    $('#codeimg').trigger("click")
                },
                complete: function () {$sub.button('reset');}
            });
        }
        
    })


});