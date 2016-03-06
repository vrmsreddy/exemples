/***********************************************
 * 插件说明：校验扩展
 * 作    者：
 * 创建日期：
 ***********************************************
 *
 * 修改人员：
 * 修改说明：
 * 修改日期：
 ***********************************************
 */
define(['jquery'],function(jQuery){

    jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: jQuery.validator.format("长度最多是 {0} 的字符串"),
        minlength: jQuery.validator.format("长度最少是 {0} 的字符串"),
        rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
        range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: jQuery.validator.format("请输入一个最大为{0} 的值"),
        min: jQuery.validator.format("请输入一个最小为{0} 的值")
    });

    jQuery.validator.addMethod("isphone", function (value, element) {
      //  var isphone = /(1(3|5|8)\d{9})|((0\d{2,3}-)?\d{7,8})/;
        var isphone = /(^1(3|5|7|8)\d{9}$)|(^(0\d{2,3}-)?\d{7,8}$)|(^400\d{6,8}$)/;
        return this.optional(element) || (isphone.test(value));
    }, "电话号码格式错误");

    jQuery.validator.addMethod("ismobile", function (value, element) {
        var ismobile = /^1[0-9]{10}$/;
        return this.optional(element) || (ismobile.test(value));
    }, "手机号码格式错误");

    // 密码校验
    jQuery.validator.addMethod("isPassword", function (value, element) {
        var isPassword = /^(\w){6,20}$/;
        return this.optional(element) || (isPassword.test(value));
    }, "密码格式为6-20个字母和数字的组合");

    // 0-100之间的数字，保留两位小数
    jQuery.validator.addMethod("royaltyRate", function (value, element) {
        var royaltyRate = /^(\d{1,2}(\.\d{1,2})?|100)$/;
        return this.optional(element) || (royaltyRate.test(value));
    }, "需输0-100之间的数，保留两位小数");

    //0-100之间的数字，保留两位小数
    jQuery.validator.addMethod("floatNumber", function (value, element) {
        var floatNumber = /^[0-9]+(.[0-9]{2})?$/;
        return this.optional(element) || (floatNumber.test(value));
    }, "需输入正数");

    //匹配integer
    jQuery.validator.addMethod("isInteger", function (value, element) {
        return this.optional(element) || (/^[-\+]?\d+$/.test(value) && parseInt(value) >= 0);
    }, "匹配数字");

    //年薪
    jQuery.validator.addMethod("checkSalaryEnd", function (value, element) {
        var salaryStart = $("#salaryStart").val();
        var salaryEnd = $("#salaryEnd").val();
        var flag = true;
        if (parseFloat(salaryStart) >= parseFloat(salaryEnd)) {
            flag = false;
        } else {
            flag = true;
        }
        return this.optional(element) || (flag);
    }, "年薪起始值不能大于年薪最大值");

    //邮箱 表单验证规则
    jQuery.validator.addMethod("mail", function (value, element) {
        var mail = /^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$/;
        return this.optional(element) || (mail.test(value));
    }, "邮箱格式不对");

    //区号验证规则
    jQuery.validator.addMethod("ac", function (value, element) {
        var ac = /^0\d{2,3}$/;
        return this.optional(element) || (ac.test(value));
    }, "区号如：010或0371");

    //无区号电话验证规则
    jQuery.validator.addMethod("noactel", function (value, element) {
        var noactel = /^\d{7,8}$/;
        return this.optional(element) || (noactel.test(value));
    }, "电话格式如：68787027");

 // 字符验证
    jQuery.validator.addMethod("normalWord", function(value, element) {
        return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
    }, "只能包括中文字、英文字母、数字和下划线");


});
