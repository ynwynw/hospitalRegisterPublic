$(function () {
    validateRule();
    $('.imgcode').click(function () {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
});

$.validator.setDefaults({
    submitHandler: function () {
        forgetPwd();
    }
});

function forgetPwd() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var username = $.common.trim($("input[name='username']").val());
    var phonenumber = $.common.trim($("input[name='phonenumber']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    $.ajax({
        type: "post",
        url: ctx + "forgetPwd",
        data: {
            "username": username,
            "phonenumber": phonenumber,
            "password": password,
            "validateCode": validateCode
        },
        success: function (r) {
            if (r.code == 0) {
                layer.alert("<font color='red'>恭喜你，您的账号 " + username + " 密码重置成功！</font>", {
                        icon: 1,
                        title: "系统提示"
                    },
                    function (index) {
                        //关闭弹窗
                        layer.close(index);
                        location.href = ctx + 'login';
                    });
            } else {
                $.modal.closeLoading();
                $('.imgcode').click();
                $(".code").val("");
                $.modal.msg(r.msg);
            }
        }
    });
}

// 手机号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
}, "请正确填写您的手机号码。");

//校验用户名
jQuery.validator.addMethod("isUserName",function(value,element){
    var userName=/^[a-zA-Z0-9]{2,13}$/;
    return this.optional(element) || (userName).test(value);
},'请输入数字或者字母,不包含特殊字符');

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#registerForm").validate({
        rules: {
            username: {
                required: true,
                isUserName: true,
                minlength: 2
            },
            phonenumber: {
                required: true,
                isPhone:true
            },
            password: {
                required: true,
                minlength: 5
            },
            confirmPassword: {
                required: true,
                equalTo: "[name='password']"
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
                minlength: icon + "用户名不能小于2个字符"
            },
            phonenumber: {
                required: icon + "请输入您的手机号",
            },
            password: {
                required: icon + "请输入您的密码",
                minlength: icon + "密码不能小于5个字符",
            },
            confirmPassword: {
                required: icon + "请再次输入您的密码",
                equalTo: icon + "两次密码输入不一致"
            }
        }
    })
}
