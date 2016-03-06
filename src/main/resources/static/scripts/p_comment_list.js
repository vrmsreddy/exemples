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
    $('#J_search').click(function () {
        console.log('---search-----');
        showList(1);
    });

    //更改状态
    $(document).off('click', '#p_comment_list .J_update').on('click', '#p_comment_list .J_update', function () {
        console.log('----status----->');
        var commentId = $(this).attr('data-id');
        var status = $(this).attr('data-status');
        if(status =="-1"){
            if(confirm('确认删除吗？')){
                var url = config.host + '/organization/comment/'+ commentId +'/update/'+ status ;
                http.post(url, {}, true,function (data) {
                    if (data.code != 200) {
                        alert(data.msg);
                        return;
                    }
                    alert('操作成功！');
                    showList(currPageNo);
                });
            }
        }else{
        var url = config.host + '/organization/comment/'+ commentId +'/update/'+ status ;
            http.post(url, {}, true,function (data) {
                if (data.code != 200) {
                    alert(data.msg);
                    return;
                }
                alert('操作成功！');
                showList(currPageNo);
            });
        }
    });

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        var startTime = $('#J_startTime').val() == ''? new Date("2015-10-03 12:00:00").format("yyyy-MM-dd hh:mm:ss") : $('#J_startTime').val();
        var endTime = $('#J_endTime').val() == ''? new Date().format("yyyy-MM-dd hh:mm:ss") : $('#J_endTime').val();
        var status = $('#J_status').val() == 0? 1:$('#J_status').val();
        console.log('--list--pageNo:' + startTime+"dddddddd"+endTime);

        http.post(config.host + '/organization/comment/list', {
            'startTime': startTime ,
            'endTime': endTime, 
            'status': status ,
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#p_comment_list').html(template('t:p_comment_list', {
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

    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#p_comment_list').html(template('t:v_empty_list', {}));
        $('#page').pagination(0,{});
    }

 });
