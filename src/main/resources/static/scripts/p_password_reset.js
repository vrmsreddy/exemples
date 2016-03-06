/**
 * p_password_reset.js
 */
// @sourceURL=p_password_reset.js
require(['config', 'http', 'strings'], function (config, http, strings) {

    $(document).on('click', '#J_update', function () {
        var oldPass = $('#J_oldPass').val();
        var newPass = $('#J_newPass').val();
        var repeatNewPass = $('#J_repeatNewPass').val();

        console.log(oldPass + " " + newPass + " " + repeatNewPass)

//        if(!strings.validLength(oldPass, 5, 20)) {
//            alert("原密码格式不正确，请重新输入!");
//            return;
//        }
//        if(!strings.validLength(newPass, 5, 20) || !strings.validLength(repeatNewPass, 5, 20)) {
//            alert("新密码格式不正确，请重新输入!");
//            return;
//        }
        if(!/^[A-Za-z0-9]{6,16}$/.test(newPass)|| !/^[A-Za-z0-9]{6,16}$/.test(repeatNewPass)){
        	alert("密码只能为字母+数字，长度为6-16!");
        	return;
        }

        if(newPass != repeatNewPass){
            alert("两次新密码输入不一致，请重新输入！");
            return;
        }
        console.log('----save new pass');
        http.post(config.host + '/sys/pass/save', {
                'oldPass' : oldPass,
                'newPass' : newPass,
                'repeatNewPass' : repeatNewPass,
            },
            true,
            function (data) {
                if (data.code != 200) {
                    return;
                }
                alert('操作成功！');
                location.reload();
            });
    });
});
