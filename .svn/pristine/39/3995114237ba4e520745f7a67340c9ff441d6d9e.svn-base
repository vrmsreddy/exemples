//sourceURL=p_topic_list.js
require([ 'config', 'jquery', 'template', 'pagination','http'], function(config,
		menubar, template, pagination,http) {

	// 当前编辑的数据ID
	var currEditId;
	var currPageNo = 1;
	var pageSize = 10;

	showList(1);

	// 搜索
	$('#queryTopicList').click(function() {
		console.log('---search-----');
		showList(1);
	});

	renderTopicStoreOptions( '/evaluation/topicStore/select/list', 'topicStoreIdV');

	// 列表
	function showList(pageNo) {
		console.log('--list--pageNo:' + pageNo);
        console.log('当前选中的'+$("#topicStoreIdV  option:selected").val());
        var tId=$("#topicStoreIdV  option:selected").val();
		http.post(config.host + '/evaluation/topic/list/', {
			'name' : $('#name').val(),
			"topicStoreId" : tId,
			'pageNo' : pageNo,
			'pageSize' : pageSize
		}, true,function(data) {
			if (data.result.length == 0) {
				showEmptyWarning();
				return;
			}
			$('#topic_list').html(template('t:v_topic_list', {
				list : data.result
			}));
			$('#page').pagination(data.totalCount, {
				items_per_page : pageSize,
				num_display_entries : 10,
				current_page : pageNo,
				// num_edge_entries: 2,
				callback : loadPageCallBack,
				callback_run : false
			});
			currPageNo = pageNo;
		});
	}
	function loadPageCallBack(index) {
		console.log('-->page_id:' + index);
		showList(index + 1);
	}
	$(document).off(
			'click',
			'#article_list .J_update').on(
			'click',
			'#article_list .J_update',
			function() {
				console.log('----update----->');
				var id = $(this).attr('data-id');
				var status = $(this).attr('data-status');
				http.post(config.host + '/article/' + id + '/update/' + status, {},true,
						function(data) {
							if (data.code != 200) {
								alert(data.msg);
								return;
							}
							alert('操作成功！');
							$('#myModal').hide();
							showList(currPageNo);
						});
			});

	// 删除
	$(document).off(
			'click',
			'#topic_list .J_removeTopic').on(
			'click',
			'#topic_list .J_removeTopic',
			function() {
				console.log('----remove----->');
				if(confirm('确认删除吗？')){
					var url = config.host + '/evaluation/topic/'
							+ $(this).attr('data-id') + '/remove'
					http.post(url, {}, true,function(data) {
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
	// 点击添加
	$("#J_add").click(function() {
		currEditId = '';
		$("#topicStoreBean :input").val('');		
		$('#J_topicBox').html(template('t:v_topic', null));
		renderTopicStoreOptions( '/evaluation/topicStore/select/list', 'J_topicStoreId');
	});
	// 保存
	$('#J_saveTopic').click(function() {
		if($("#FormBean").valid()){
				console.log('-->save-----');
				if($("#J_topicStoreId").val()==0){
					alert("请选择题库");
					return;
				}
				
				var options = [];
				var valid = true;
				$('#FormBean .J_opition').each(function(){
					var name = $(this).val();
					var point = $(this).parent().parent().find('.J_opition_point').val();
					if($.isEmptyObject($.trim(name))){
						alert("选项不能为空");
						valid=false;
						return;
					}
					if($.isEmptyObject($.trim(point))){
						alert("分值不能为空");
						valid=false;
						return;
					}
					options.push({'name': name, 'point' : point});
				});
				if(valid){
				http.post(config.host + '/evaluation/topic/save', {
					'id' : currEditId,
					'name' :$("#J_name").val(),
					'type' : $("input:radio[name='J_type']:checked").val(),
					'topicStoreId' : $("#J_topicStoreId").val(),
					'options' : options

				}, true,function(data) {
					if (data.code != 200) {
						alert(data.msg);
						return;
					}
					alert('操作成功！');
					$('#myModal').modal('hide');
					if(currEditId != ''){
				        showList(currPageNo);
				    }else{
				        showList(1);
				    }
				});
				}}});

	// 编辑
	$(document).off('click', '#topic_list .J_edit').on('click', '#topic_list .J_edit', function() {
		// 设置当前编辑的数据ID
		currEditId = $(this).data('id');
		console.log('---edit----->' + currEditId);
		
		http.get(config.host + "/evaluation/topic/" + currEditId,{}, true, function(data){
			if (data.code !=200){
                alert(data.msg);
                return;
            }
            //渲染选项列表
            $('#J_topicBox').html(template('t:v_topic', {options:data.result.options}));;
            renderTopicStoreOptions( '/evaluation/topicStore/select/list', 'J_topicStoreId');

            $('#J_name').val(data.result.name);
            $('#J_topicStoreId').find('option[value="' + data.result.topicStoreId + '"]').attr('selected', 'true');
            $('input:radio[name="J_type"][value="'+data.result.type+'"]').attr('checked', true);

            //删除添加节点
            $('#J_addOptionBox').remove();
            //删除变添加
            $removeBox = $('.J_optionLine').last();
            $removeBox.find('.J_removeOption').text('添加').removeClass('J_removeOption').addClass('J_addOption');
            $removeBox.removeClass('J_removeOption').attr('id','J_addOptionBox');
		});
	});

	// 编辑题目-添加选项
    $(document).off('click', '#J_topicBox .J_addOption').on('click', '#J_topicBox .J_addOption', function () {
        console.log('----add topicoption----->');

        // 复制“待添加选项”区域对象作为“已添加的选项”的模板，并进行赋值等
        $addBox = $('#J_addOptionBox').clone().removeAttr('id').addClass('J_optionLine');
        $addBox.find('.J_addOption').text('删除').removeClass('J_addOption').addClass('J_removeOption');
        $('#J_addOptionBox').before($addBox);
        $('#J_addOptionBox :input').val('');
    });

    // 编辑题目-删除选项
    $(document).off('click', '#J_topicBox .J_removeOption').on('click', '#J_topicBox .J_removeOption', function () {
        console.log('----remove option----->');
        $(this).parents('.J_optionLine').remove();
    });

	// 渲染添加、编辑题库时的下拉框待选项
    function renderTopicStoreOptions(url,selectBoxId) {
        console.log('---->selectBoxId:' + selectBoxId);
        http.post(config.host + url, {}, false, function (data) {
                $('#' + selectBoxId).html(template('t:v_topic_store_list', {list: data.result}));
            });
    }
	// 展示列表为空的时候的提示信息
	function showEmptyWarning() {
		$('#topic_list').html(template('t:v_empty_list', {}));
		$('#page').pagination(0,{});
	}
});
