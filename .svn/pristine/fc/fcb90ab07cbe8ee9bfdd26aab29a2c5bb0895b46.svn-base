require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination,http) {
	template.helper('stripStr', function (str) {
	    // 处理字符串...
		if (str.length>=30){
			str=str.substring(0,30)+"...";
		}
	    return str;
	});
    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    showList(1);

    // 搜索
    $('#queryFeedbackList').click(function () {
        console.log('---search-----');
        showList(1);
    });
 

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        var status=$("#feedbackStatus  option:selected").val();
        console.log('status'+status+'mobile'+$("#mobileF").val());
        http.post(config.host + '/feedback/list/', {
            'status': status,
            'mobile':$("#mobileF").val(),
            'pageNo': pageNo,  
            'pageSize': pageSize
        },true, function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#feedback_list').html(template('t:feedback_list', {
                list: data.result
            }));
            $('#page').pagination(data.totalCount, {
                items_per_page: pageSize,
                num_display_entries: 10,
                current_page: pageNo,
//                num_edge_entries: 2,
                callback: loadPageCallBack,
                callback_run: false
            });
            currPageNo = pageNo;
        });
    }
    function loadPageCallBack(index) {
        console.log('-->page_id:'+index);
        showList(index + 1);
    }
    $(document).on('click','#feedback_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/feedback/' + id + '/handle', {},true, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').hide();
            showList(currPageNo);
        });
    });
    

   
 
    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#feedback_list').html($('#v_empty_list').html());
        $('#page').pagination(0,{});
    }  
});
