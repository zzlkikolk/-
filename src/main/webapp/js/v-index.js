/*
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: 鱼小陌
 * @Date: 2019-09-04 19:25:07
 * @LastEditors: 鱼小陌
 * @LastEditTime: 2019-09-07 11:18:24
 */
$(function() {
  uiHadler();
  formHandeler();
});

// 表单验证
var formHandeler = function() {
  // 节点
  var $name = $("#name"),
    $phone = $("#phone"),
    $prove = $("#prove"),
    $sex = $("#sex"),
    $unit = $("#unit"),
    $code = $("#code");
  // 开关标记
  var flag = false;

  // 参数
  var u_name = u_sex = u_id = a_id = phone =  u_prove= code = null;

  // 验证
  function testStr(node, regstr) {
    var val = node.val();
    var reg = new RegExp(regstr);
    if (reg.test(val)) {
      node
        .parent()
        .removeClass("error")
        .addClass("success");
      flag = true;
    } else {
      node
        .parent()
        .removeClass("success")
        .addClass("error");
      flag = false;
    }
  }

  // 添加按钮监听
  $("#sub").click(function() {
    // 提交按钮
    var $btn = $(this);
    // 消息显示框
    var $alert = $("#alertResult");
    // 获取邀请人id
    a_id = $.getUrlParam("a");
    // 性别
    u_sex = $sex.val();
    // 单位
    u_id = $unit.val();
    // 验证姓名
    u_name = $name.val();
    testStr($name, /^[\u4e00-\u9fa5]{2,6}$/);
    // 验证号码
    phone = $phone.val();
    testStr($phone,/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/);
    // 验证身份证
    prove = $prove.val();
    testStr($prove, /(^\d{18}$)|(^\d{17}(\d|X|x)$)/);
    // 验证code
    code = $code.val();
    testStr($code, /^[A-Za-z0-9]{5}$/);

    if (flag) {
      // 成功
      $btn.button("loading");
      $.ajax({
        type: "POST", //默认get
        url: "userRegister", //默认当前页
        data: {
          u_name: u_name,
          u_sex: u_sex,
          u_id: u_id,
          a_id: a_id,
          phone: phone,
          u_prove: prove,
          code: code
        }, //格式{key:value}
        dataType: "json",
        success: function (res) {  //请求成功回调
          $alert.removeClass("hidden");
          if(res.status==200){
            // 显示信息
            $alert.removeClass('alert-warning').addClass('alert-success');
            // 跳转url
            window.location.href = "./"+res.data;
          }else {
            $('#codeimg').trigger("click");
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
        complete: function () {$btn.button('reset');}
      });
    }
  });
};

// Ui有关
var uiHadler = function() {
  // 输入框
  $("input").focus(function() {
    $(this)
      .parent()
      .addClass("def");
    $('footer').addClass("reset");
  });
  $("input").blur(function() {
    $('footer').removeClass("reset");
  });
  // 下拉框
  $("select").focus(function() {
    $(this)
      .parent()
      .addClass("def");
  });
  // 工具提示
  $('[data-toggle="tooltip"]').tooltip();
  // 验证码
  var codeImgUrl = "getImg?t=";
  $("#codeimg")
    .click(function() {
      $(this).attr("src", codeImgUrl + new Date().getTime())
    })
    .attr("src", codeImgUrl + new Date().getTime());
  // alert不删除隐藏起来
  $("div.alert .close").click(function() {
    $(this)
      .parent()
      .removeClass("show")
      .addClass("hidden");
  });
};
