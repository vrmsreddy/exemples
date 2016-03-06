//@sourceURL=p_evaluation.js
require(['config', 'jquery', 'template', 'pagination','validate_extend','http','jquery.bootstrap'], function (config, menubar, template, pagination,validate, http) {
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
    
    $("#UserFormBean").validate({

      submitHandler: function(form){

        //loading('正在提交，请稍等...');
      ~function () {
            console.log('--save--');

            var topicStore = [];
            var tmpStore={};
            var noRepeat = true;
            $('#J_editBox .J_topicStoreLine').each(function(){
                var id = $(this).find('.J_topicStoresToAdd').val();
                var topicNum = $(this).find('.J_topicNum').val();
                if(id){
                	if(tmpStore[id]){
                		alert("只准选择一份["+$.trim(tmpStore[id])+"]题库的题目");
                		noRepeat=false;
                	}else{
                		tmpStore[id]=$(this).find('.J_topicStoresToAdd').find("option:selected").text();
                	}
                	
                  topicStore.push({'topicStoreId': id, 'topicNum': topicNum});
                }
            });
            if(!noRepeat){
            	return;
            }
            if(topicStore.length==0)
            {
            alert("至少设置一个有效题库");
            return ;
            }

            http.post(config.host + '/evaluation/save', {
                'id': currEditId,
                'name': $('#J_name').val(),
                'detail': $('#J_detail').val(),
                'topicStoreList': topicStore
            }, true,function (data) {
                alert('操作成功！');
                $("#UserFormBean").get(0).reset();
                $('#myModal').modal('hide');//('hide');
                showList(currPageNo);
            });
        }();
        //form.submit();
      }
    });

    showList(1);

    // 添加
    $('#J_add').click(function () {
        console.log('---add-----');
        // clear last editId
        currEditId = '';
        var allTopicStores = fetchAllValidTopicStores();
        $('#J_editBox').html(template('t:v_evaluation', {allTopicStores: allTopicStores}));
        var topicNum = $('#J_editBox .J_topicStoresToAdd :selected').data('topicNum');
        $('#J_editBox .J_topicNum').val(topicNum);
    });

    function fetchAllValidTopicStores(){
        var result;
        http.post(config.host + '/evaluation/topicStore/list', {'pageNo': 1, 'pageSize': 50}, false,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            //注意：必须使用变量做中转，否则无法返回数据
            result = data.result;
        });
        return result;
    }
    // 选择不同的题库时切换对应的题目数量
    $(document).off('change', '#J_editBox .J_topicStoresToAdd').on('change', '#J_editBox .J_topicStoresToAdd', function () {
        var topicNum = $(this).find(':selected').data('topicNum');
        $(this).parents('.J_topicStoreLine').find('.J_topicNum').val(topicNum);
    })

    // 编辑评测
    $(document).off('click', '#evaluation_list .J_edit').on('click', '#evaluation_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->' + currEditId);

        http.get(config.host + '/evaluation/' + currEditId, {},false, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }

            // 在返回的测评数据中加入题库列表数据，用于模板渲染
            data.result['allTopicStores'] = fetchAllValidTopicStores();
            $('#J_editBox').html(template('t:v_evaluation', data.result));
            	 
            //
            $('#J_editBox .J_topicStoresToAdd').each(function () {
                var topicStoreId = $(this).data('topicStoreId');
                $(this).find('option[value="' + topicStoreId + '"]').attr('selected', true);
            })
        });
    });

    // 编辑评测-添加题库
    $(document).off('click', '#J_editBox .J_addTopicStore').on('click', '#J_editBox .J_addTopicStore', function () {
        console.log('----add topicStore----->');

        // 复制“待添加题库”区域对象作为“已添加的题库”的模板，并进行赋值等
        $addBox = $('#J_addTopicStoreBox').clone().removeAttr('id');
        $addBox.find('.J_addTopicStore').text('删除').removeClass('J_addTopicStore').addClass('J_removeTopicStore');
        $addLine = $(this).parents('.J_topicStoreLine');
        $sel = $addLine.find('.J_topicStoresToAdd');
        $addBox.find('option[value="' + $sel.val() + '"]').attr('selected', true);

        // 重置“待添加题库”对象，将“已添加题库”插入该对象前面
        $sel.find('option[value=""]').attr('selected', true);
        $addLine.find('.J_topicNum').val('');
        $('#J_addTopicStoreBox').before($addBox);
    });

    // 编辑评测-删除题库
    $(document).off('click', '#J_editBox .J_removeTopicStore').on('click', '#J_editBox .J_removeTopicStore', function () {
        console.log('----remove topicStore----->');
        $(this).parents('.J_topicStoreLine').parent().remove();
    });

    // 删除评测
    $(document).off('click', '#evaluation_list .J_removeEva').on('click', '#evaluation_list .J_removeEva', function () {
        console.log('----remove----->');
        if(confirm('确认删除吗？')){
            var url = config.host + '/evaluation/' + $(this).attr('data-id') + '/remove'
            http.post(url, {}, true,function (data) {
                if (data.code != 200) {
                    alert(data.msg);
                    return;
                }
                alert('操作成功！');
                $('#myModal').hide();
                showList(currPageNo);
            });
        }
    });

    $(document).off('click', '#evaluation_list .J_update').on('click', '#evaluation_list .J_update', function () {
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status = $(this).attr('data-status');
        http.post(config.host + '/evaluation/' + id + '/update/' + status, {}, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').hide();
            showList(currPageNo);
        });
    });

    // 搜索
    $('#queryEvaluationList').click(function () {
        console.log('---search-----');
        showList(1);
    });


    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/evaluation/list', {
            'name': $('#evaName').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#evaluation_list').html(template('t:evaluation_list', {
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
        console.log('-->page_id:' + index);
        showList(index + 1);
    }

    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#evaluation_list').html($('#v_empty_list').html());
        $('#page').pagination(0,{});
    }
});
