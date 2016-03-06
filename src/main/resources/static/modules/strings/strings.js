/**
 * 字符串格式验证.
 */
// @sourceURL=strings.js
define(['jquery'],function($){
    var isEmpty = function(str){
        if(!str && str.trim().length == 0) {
            return true;
        }
        return false;
    };

    return {
        isEmpty : isEmpty,
        /**
         * [min, max)
         */
        validLength : function(str, min, max){
            if(!isEmpty(str) && str.trim().length >= min && str.trim().length < max) {
                return true;
            }
            return false;
        }
//        validEmail : function(str) {
//            var reg = /w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*/;
//            if(str.match(reg)){
//                alert('邮箱格式不对');
//                return false;
//            }
//        }
    }
});
