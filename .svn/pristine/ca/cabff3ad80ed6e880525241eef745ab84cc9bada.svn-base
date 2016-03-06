/**
 * 统一的菜单栏
 */
define(['jquery'],function($){
    return {
        showMenu :function(userPhoto){
             //滑过，背景变成白色
        $('.leftsidebar_box dt').hover(function(){
            $(this).css('background-color','#fff');
        },function(){
            $(this).css('background-color','#e9e9e9');
        });

        $("#user-photo").attr("src", userPhoto)
        
        //点击改变背景颜色
        $(".leftsidebar_box dt").click(function(){
            $(".leftsidebar_box dt").css({"background-color":"#e9e9e9",'color':'#585858'});
            $(this).css('color','#00B38A');
            $('.leftsidebar_box dt').css('border-top-color','#ffffff');
            var index = $('.leftsidebar_box dt').index($(this));
            $('.leftsidebar_box dt').eq(index+1).css('border-top-color','#e5e5e5');
            $(this).parent().find('dd').removeClass("menu_chioce");
            // 滑过改变dd的背景颜色
            $(this).parent().find('dd').hover(function(){
                $(this).css('opacity','0.8');
                $(this).css('background-color','#ffffff');
                $(this).find('a').css('color','#00B38A');
            },function(){
                $(this).css('opacity',0.6);
                $(this).css('background-color','#e9e9e9');
                $(this).find('a').css('color','#585858');
            });
            $(".leftsidebar_box dt .right").removeClass('glyphicon-chevron-down');
            $(this).parent().find('.right').addClass('glyphicon glyphicon-chevron-down');
            $(".menu_chioce").slideUp(); 
            $(this).parent().find('dd').slideToggle();
            $(this).parent().find('dd').addClass("menu_chioce");
        });
        }
    }
 })