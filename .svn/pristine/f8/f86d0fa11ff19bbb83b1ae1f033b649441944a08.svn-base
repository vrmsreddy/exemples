/**
 * login.js
 */
//@sourceURL=login.js
require.config({
    paths: {
        "jquery": "../plugins/jquery/2.1.4/jquery.min",
        "config": "config",
        "cookie": "../modules/cookie/cookie",
        "validate": "../modules/validate/j.validate",
        "validate_extend": "../modules/validate/j.validate.extend",
        "http": "../modules/http/http"
    }
});

// 在当前页面注入被依赖模块
require(['config', 'validate', 'cookie', 'http'], function (config, validate, cookie, http) {
    // 首页加载页首页脚
    $("#common-header").load("../common/header.html?version=" + (new Date()).getTime(), function(){
        $("#ops-user-menu ul").hide();
    });
    $("#common-footer").load("../common/footer.html?version=" + (new Date()).getTime());

    console.log('---enter login page----');

    //登录验证
    $("#form-box").validate({
        debug: false,//添加该属性，点击提交时，只进行检验，不会进行提交
        rules: {//校验，对应的事input里面的"name属性"
            username: {
                required: true,
                minlength: 5,
                maxlength: 20
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "账户不能为空",
                minlength: "账户名至少5位",
                maxlength: "账户名最多20位"
            },
            password: {
                required: "密码不能为空"
            }
        },
        submitHandler: function () {//通过验证后运行的函数，可以加上表单提交的方法，这里可以使Ajax请求
            var username = $("#username").val();
            var password = $("#password").val();

            http.post(config.host + "/login", {
                'username': username,
                'password': password
            }, true, function (data) {
                    console.log(data)
                    if (data.code == 200) {
                        // 将数据存储在cookie中
                        cookie.setCookie("userId", data.result.id);
                        cookie.setCookie("nickname", data.result.nickname);
                        cookie.setCookie("userType", data.result.type);
                        window.location.href = config.host + '/html/index.html';
                        return;
                    } else {
                        cookie.setCookie("userId", null);
                        cookie.setCookie("nickname", null);
                        cookie.setCookie("userType", null);
                        alert(data.msg);
                    }
                });
        }
    });
});
