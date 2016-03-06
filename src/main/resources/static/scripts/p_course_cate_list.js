//@sourceURL=p_course_cate_list.js
require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination,http) {

    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    renderCateOptions('J_queryParentCate');
    showList(1);


    // 搜索
    $('#J_search').click(function () {
        console.log('---search-----');
        showList(1);
    });
    // 添加
    $('#J_add').click(function () {
        console.log('---add-----');
        // clear last editId
        currEditId = '';
        $('#editForm').html(template('t:v_courese_cate', {}));
        initParentCateForEdit();
    });
    // 上传图片
     $(document).off("change",'#J_upload').on("change",'#J_upload', function uploadFile() {
        $.ajax({
            url: config.host + "/file/upload",
            type: "POST",
            data: new FormData($("#editForm")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
            	if(data.code==500){
          		  alert("上传的图片超过最大2M限制");
          		  return;
          	  }
                $('#J_uploadImg').attr('src', config.host + '/' + data.result.path);
                $('#J_imgPath').val(data.result.id);
                $("#upload-file-message").text("上传成功");
            },
            error: function () {
                $("#upload-file-message").text("上传失败)");
            }
        });
    });


    // 编辑
    $(document).off('click', '#p_cate_list .J_edit').on('click', '#p_cate_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->' + currEditId);

        // 渲染页面
        $('#editForm').html(template('t:v_courese_cate', {}));
        $('#J_imgPath').val($(this).data('resource-id')) ;
        $('#J_name').val($(this).data('name'));
        $('#J_priority').val($(this).data('sort'));
        var picUrl = $(this).data('picUrl');
        if (picUrl.indexOf('http://') < 0) {
            picUrl = config.host + '/' + picUrl;
        }
        $('#J_uploadImg').attr('src', picUrl);
        initParentCateForEdit();
        // 选中旧的父分类
        var oldParentId = $(this).data('parentCateId');
        $('#J_parentCate option[value="' + oldParentId + '"]').attr('selected', true);

        if ($(this).data('homeShow') > 0) {
            $('#J_homeShow').attr('checked', true);
        } else {
            $('#J_homeShow').attr('checked', false);
        }
    });

    function initParentCateForEdit() {
        if ($('#J_parentCate').children().length > 0) {
            return;
        }
        console.log('---init parentCate for edit');
        renderCateOptions('J_parentCate');
    }
    // 渲染父类目下拉框待选项
    function renderCateOptions(selectBoxId) {
        console.log('---->renderOptions:' + selectBoxId);
        $.ajax({
            url: config.host + '/organization/category/parent/list/',
            type: 'get',
            dataType: 'json',
            async: false,
            xhrFields: {
                withCredentials: true // 确保请求会携带上Cookie
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Content-Type', 'application/json'); // 设置请求协议，会触发PreFlight
            },
            success: function (data) {
                $('#' + selectBoxId).html(template('t:v_parent_cate_list', {list: data.result}));
            },
            error: function (data) {
            }
        });
    }

    // 删除
    $(document).off('click', '#p_cate_list .J_removeCate').on('click', '#p_cate_list .J_removeCate', function () {
        console.log('----remove----->');
        if(confirm('确认删除吗？')){
        var url = config.host + '/organization/category/' + $(this).attr('data-id') + '/remove'
        http.post(url, {}, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功');
            $('#myModal').hide();
            showList(currPageNo);
        });
        }
    });

    // 保存
    $(document).off('click','#J_saveCate').on('click','#J_saveCate',function () {
    	console.log('-----save------>');
    	if(""==$.trim($('#J_name').val())){
    		alert("请填写课程分类名称");
    		return;
    	}
    	if(!/^[\u0391-\uFFE5\w]+$/.test($.trim($('#J_name').val())))
    		{
    		alert("名称只能包括中文字、英文字母、数字和下划线");
    		return ;
    		}
    	if(""==$.trim($('#J_imgPath').val())){
    		alert("请上传图片");
    		return;
    	}
    	
    	
        http.post(config.host + '/organization/category/save', {
            'id': currEditId,
            'name': $('#J_name').val(),
            'order': $('#J_priority').val(),
            'parentId': $('#J_parentCate :selected').val(),
            'resourceId': $('#J_imgPath').val(),
            'homePageShow': $('#J_homeShow').is(':checked')
        }, true,function (data) {
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
    });

    //列表
    function showList(pageNo) {
        var parentCateId = $('#J_queryParentCate').val();
        console.log('--list--parentCate=' + parentCateId);

        http.post(config.host + '/organization/category/list', {
            'pageNo': pageNo,
            'pageSize': pageSize,
            'parentCateId': parentCateId == '' ? 0 : parentCateId
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#p_cate_list').html(template('t:p_cate_list', {
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
        $('#p_cate_list').html(template('t:v_empty_list',{}));
        $('#page').pagination(0,{});
    }

});
