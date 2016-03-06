/**
 *
 */
// @sourceURL=cookie.js
define(['jquery'],function($){

    return {
        setCookie: function(key, value){
            var expires = new Date();
            expires.setTime(expires.getTime() + (1 * 24 * 60 * 60 * 1000));
            if(value == null) {
                document.cookie = key + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
            }else{
                document.cookie = key + '=' + value + ';expires=' + expires.toUTCString();
            }
        },

        getCookie: function(key){
            var keyValue = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
            return keyValue ? keyValue[2] : null;
        }
    }
});
