/**
 * index.js
 */
require.config({
    urlArgs:"version="+ver(),
  paths: {
    "config": "config",
    "jquery": "../plugins/jquery/2.1.4/jquery.min",
    "template": "../modules/template/template-min",
    "pagination": "../modules/pagination/pagination",
    "dateFormat": "../modules/dateFormat/dateFormat",
    "validation": "../modules/validation/validation",
    "http": "../modules/http/http",
    "strings": "../modules/strings/strings",
    "menubar": "../modules/menubar/menubar",
    "cookie": "../modules/cookie/cookie",
    "validate": "../modules/validate/j.validate",
    "validate_extend": "../modules/validate/j.validate.extend",
      "jquery.bootstrap": "../plugins/metro-bootstrap/3.2.0/bootstrap.min"
  },
  shim: {
        "jquery.bootstrap": {
            deps: ["jquery"]
        },
        "validate_extend":{
          deps:["validate"]
        }
    }
});

// 在当前页面注入被依赖模块
require(['config','menubar','jquery', 'cookie','http','jquery.bootstrap','validate_extend'], function(config, menubar, $, cookie,http) {
  // 首页加载页首页脚
  $("#common-header").load("../common/header.html?version=" + ver(), function(){
        // 判断是否已经登陆，如果没有则跳转到login页面.
        var userId = cookie.getCookie("userId")
        if(!userId || userId == 0) {
          window.location.href = config.host + '/html/login.html?version=' + ver();
        }else{
          console.log("欢迎 " + cookie.getCookie("nickname") + " 登陆")
          $("#nickname").text(cookie.getCookie("nickname"))
        }
  });
  $("#common-footer").load("../common/footer.html?version=" + ver());

  // 加载菜单栏, 把菜单栏的菜单按钮绑定到具体的html页面
  $('.wrap').load('../modules/menubar/menubar.tpl', function () {
    menubar.showMenu("../images/tmp/user.png");
    var index = 0;
    $('.leftsidebar_box dd').each(function () {

      // 检查权限，控制菜单显示
      var isOutside = cookie.getCookie("userType") == 0;
      console.log('isOUT---->' + isOutside);
      var tagx = $(this).attr("tagx");
      if (isOutside && (tagx == 'p_organ_list' || tagx == 'p_course_list')) {
        $(this).parent().removeClass('hide');
        $(this).removeClass('hide');
      }
      if (!isOutside) {
          $(this).parent().removeClass('hide');
          $(this).removeClass('hide');
      }

      var self = $(this).parent();
      $(this).click(function () {
        $(this).addClass('white').siblings().removeClass('white');
        var tagx = $(this).attr("tagx") + ".html?version=" + ver();
        index = $('.leftsidebar_box dd').index($(this));
        rightLoad(tagx);
      });
    });
  });

    var organId = getUrlParameter('organId');
    console.log('---index.js--->organ:' + organId);
    if (organId) {
        var $menu = $('.leftsidebar_box dd').find('[tagx="p_course_list"]');
        $menu.trigger($.Event("click"));

        //$('.leftsidebar_box dd').find('[tagx="p_course_list"]').addClass('white').siblings().removeClass('white');
        $('.right-wrap').data('organId', organId).load('p_course_list.html',function(){
            //$('#QueryCourseNameForm').hide();
        });
    } else {
        // 加载欢迎页
        $('.right-wrap').load('p_welcome.html');
    }

    function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };

   // 根据当前选择的菜单，决定要加载内容块
  function rightLoad(urlValue){
      $('.right-wrap').data('organId', '').load(urlValue);
  }

  $(document).on('click', '#logout', function () {
      console.log('----logout----->');
      http.post(config.host + '/logout', {}, true, function (data) {
          if (data.code != 200) {
              alert(data.msg);
              return;
          }
          alert('退出成功！');
          cookie.setCookie("userId", null);
          cookie.setCookie("nickname", null);
          window.location.href = config.host + '/html/login.html?version=' + ver();
        });
    });
});
