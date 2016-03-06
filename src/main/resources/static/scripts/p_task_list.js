require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination,http) {

    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    showList(1);

    // 搜索
    $(document).off('click','#J_search').on('click','#J_search', function(){
        console.log('---search-----');
        showList(1);
    });
	renderAreaOptions("topicStoreIdV");

    // 渲染题库下拉框待选项
    function renderAreaOptions(selectBoxId) {
        console.log('---->selectBoxId:' + selectBoxId);
        http.post(config.host + '/evaluation/topicStore/select/list', {}, false, function (data) {
                $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
            });
    }

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        var tId=$("#topicStoreIdV  option:selected").val();
        http.post(config.host + '/evaluation/task/list/', {
            'name': $('#taskName').val(),
            'topicStoreId':tId,
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#task_list').html(template('t:task_list', {
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
    $(document).off('click','#task_list .J_update').on('click','#task_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/evaluation/task/' + id + '/update/' + status, {}, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').hide();
            showList(currPageNo);
        });
    });

    //删除
    //$(document).on('click', '#topic_store_list .J_remove', function () {
    //    console.log('----remove----->');
    //    var url = config.host + '/evaluation/topicStore/' + $(this).attr('data-id') + '/remove'
    //    http.post(url, {}, function (data) {
    //        if (data.code != 200) {
    //            alert(data.msg);
    //            return;
    //        }
    //        alert('操作成功！');
    //        $('#myModal').hide();
    //        showList(1);
    //    });
    //});
    //点击添加
    $("#TaskAdd").click(function(){
    	 $('#TaskFormBean').get(0).reset();
    	 $("#J_uploadImg").attr("src","");
    	 $("#J_imgPath").val("");
    	currEditId='';
        renderAreaOptions("topicStoreIdK");

    });

    // 保存
    $('#saveTaskBtn').click(function () {
    	if($("#TaskFormBean").valid()){
    		if($("#topicStoreIdK").val()==0)
    			{
    			alert("请选择题库");
    			return;
    			}
    		if($("#J_imgPath").val()==""){
    			alert("请上传图片");
    			return;
    		}
    		if($.trim($("#J_video").val())==""){
    			alert("请填写视频");
    			return;
    		}
    		
    		if($.trim($("#detail").val())==""){
    			alert("请填写详情！");
    			return;
    			
    		}
        http.post(config.host + '/evaluation/task/save', {
            'id': currEditId,
            'name': $("#TaskFormBean [name='name']").val(),
            'topicStoreId': $("#TaskFormBean  [name='topicStoreId']").val(),
            'hardType': $("#TaskFormBean [name='hardType']").val(),
            'videoPath': $('#J_video').val(),
            'picRescoureId': $('#J_imgPath').val(),
            'videoResourceId': 0,
            //'picResourceId':0,
            'detail':$("#TaskFormBean [name='detail']").val()

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
    }});

    // 编辑
    $(document).off('click', '#task_list .J_edit').on('click', '#task_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);
        http.get(config.host + '/evaluation/task/'+currEditId, {
             

        }, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            console.info(data);
            data = data.result;
            $("#TaskFormBean [name='name']").val(data.name);
            $("#TaskFormBean  [name='videoPath']").val(data.videoPath);
            $("#TaskFormBean [name='detail']").val(data.detail);
            $("#TaskFormBean [name='hardType']").val(data.hardType);
            $("#J_uploadImg").attr("src",data.picPath);
            renderAreaOptions("topicStoreIdK");
            $("#TaskFormBean [name='topicStoreId']").val(data.topicStoreId);
            $("#J_imgPath").val(data.picRescoureId);
            $("#myModal").modal('show');
            
            
            
        });
        
        
       
       
//        $("#topicStoreBean [name='trainPlanName'] ").val($(this).data('plan'));

    });
    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#task_list').html(template('t:v_empty_list', {}));
        $('#page').pagination(0,{});
    }
    // 上传图片
    // 上传图片
    $(document).off("change",'#J_upload').on("change",'#J_upload', function uploadFile() {
       $.ajax({
           url: config.host + "/file/upload",
           type: "POST",
           data: new FormData($("#TaskFormBean")[0]),
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
});
