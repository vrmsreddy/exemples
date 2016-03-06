require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination, http) {

    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    showList(1);

    // 搜索
    $('#queryArticleList').click(function () {
        console.log('---search-----');
        showList(1);
    });

    // 初始化文本编辑器
    initRichEditor();

    // 为tinymce增加图片上传功能，并绑定图片上传按钮的事件
    $("#image-uploader").unbind("change").change(function(){
        console.log(new FormData($("#image-upload-form")[0]))
        var data = new FormData()
        $.each($("#image-uploader")[0].files, function(index, f){
            data.append('file', f);
        });
        $.ajax({
            url: config.host + "/file/uploadToOSS",
            type: "POST",
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
            	if(data.code==500){
          		  alert("上传的图片超过最大2M限制");
          		  return;
          		  
          	  }
                data.result['path'] = data.result['path'];
                // 将上传后的图片地址保存到当前的图片上传区域，如果此时取消，就会产品垃圾数据
                // 垃圾数据可以通过定时任务定期清理.
                $("#"+tinymce.currentImageFieldName).val(data.result['path']);
            },
            error: function () {
                alert("上传失败)");
            }
        });
    });

    // 列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/article/list/', {
            'keyword': $('#articleName').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true, function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#article_list').html(template('t:article_list', {
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

  // 编辑
  $(document).off('click', '#article_list .J_edit').on('click', '#article_list .J_edit', function () {
    // 设置当前编辑的数据ID
    currEditId = $(this).data('id');
    console.log('---edit----->' + currEditId);

    http.get(config.host + '/article/' + currEditId, {}, false, function (data) {
      if (data.code != 200) {
        return;
      }
      //$('#J_editBox').html(template('t:edit_article', data.result));
      var a = data.result;
      $("#articleFormBean [name='title']").val(a['title']);
      $("#articleFormBean [name='priority']").val(a['priority']);
      $('#J_author').val(a['author']);
      $("#articleFormBean [name='profile'] ").val(a['profile']);
      $("#articleFormBean [name='type'] ").val(a['type']);
      $("#articleFormBean [name='url'] ").val(a['url']);
      $("#J_isBanner").val(a['isBanner']);
      // 机构列表
      renderOrganOptions();
      var oldOrganId = a['organizationId'];
      if (oldOrganId && oldOrganId > 0) {
        $('#J_orgId option[value="' + oldOrganId + '"]').attr('selected', true);
      }
      // 文章主图
      $('#J_imgResource').val(a['resourceId']);
      var picPath = a['picPath'];
      if (picPath && picPath.length > 0) {
        if (picPath.indexOf('http://') < 0) {
          picPath = config.host + '/' + picPath;
        }
        $('#J_uploadImg').attr('src', picPath);
      }

      // 文章详情
      // 把textare的内容加载到编辑器.
      tinymce.get('detail').setContent(a['detail']);
    });
  });

    // 渲染添加、编辑分类时的下拉框待选项
    function renderOrganOptions() {
        if ($('#J_orgId').children().length > 1) {
            return;
        }
        http.get(config.host + '/organization/valid/list', {}, false, function (data) {
            if (data.code != 200) {
                alert('无法获取到机构列表');
            }
            $('#J_orgId').html(template('t:option_list', {list: data.result}));
        })
    }

  // 上传图片
  $('#J_uploadMainPic').on("change", function uploadFile() {
    $.ajax({
      url: config.host + "/file/upload",
      type: "POST",
      data: new FormData($("#articleFormBean")[0]),
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
        $('#J_imgResource').val(data.result.id);
        $("#upload-file-message").text("上传成功");
      },
      error: function () {
        $("#upload-file-message").text("上传失败)");
      }
    });
  });

    //保存
    $("#saveArticleBtn").unbind('click').click(function(){
      if($("#articleFormBean").valid()){
    	  var type = $("#articleFormBean [name='type'] ").val();
    		if(type==2){
    			 if($.isEmptyObject($('#J_orgId').val())){
    				 alert("类型为机构时，机构字段必选");
    				 return;
    			 }
    		}
    		if(type==3){
    			if($.isEmptyObject($("#articleFormBean [name='url'] ").val())){
   				 alert("类型为第三方页面时，url字段必填");
   				 return;
   			 }
    		}
    		if($.isEmptyObject($('#J_imgResource').val())){
    			alert("请上传图片");
    			return;
    		}
    	 http.post(config.host + '/article/save', {
             'id': currEditId,
             'title': $("#articleFormBean [name='title']").val(),
             'priority': $("#articleFormBean  [name='priority']").val(),
             'author': $("#articleFormBean [name='author']").val(),
             'profile': $("#articleFormBean [name='profile'] ").val(),
             'type': $("#articleFormBean [name='type'] ").val(),
             'organizationId': $('#J_orgId').val(),
             'url': $("#articleFormBean [name='url'] ").val(),
             'isBanner': $("#articleFormBean [name='isBanner'] ").val(),
             'resourceId':$('#J_imgResource').val(),
              'detail' : tinymce.get('detail').getContent()
         }, true, function (data) {
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
    }})

    // 点击添加
  $('#articleAdd').click(function () {
    currEditId = '';
    $("#articleFormBean :input").val('');
    $('#J_type option[value=1]').attr('selected', true);
    $('#J_isBanner option[value=0]').attr('selected', true);
    $('#J_uploadImg').attr('src', '');
    tinymce.get('detail').setContent('');
    // 机构列表
    renderOrganOptions();
  });
    $(document).off('click','#article_list .J_update').on('click','#article_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/article/' + id + '/update/' + status, {}, true, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $("#articleFormBean").get(0).reset();
            $('#myModal').hide();
            showList(currPageNo);
        });
    });


    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
      $('#article_list').html(template('t:v_empty_list', {}));
      $('#page').pagination(0,{});
    }

});

/**
 * 初始化文本编辑器.
 */
function initRichEditor(){
    tinymce.init({
        selector: 'textarea',
        height: 500,
        plugins: [
        'advlist autolink lists link image charmap print preview anchor',
        'searchreplace visualblocks code fullscreen',
        'insertdatetime media table contextmenu paste code'
        ],
        toolbar: 'insertfile undo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
        content_css: [
        '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
        '//www.tinymce.com/css/codepen.min.css'
        ],
        file_browser_callback: function(field_name, url, type, win) {
            if(type=='image'){
                // 暂存当前tinymce图片域的id，便于图片上传成功后使用图片.
                tinymce.currentImageFieldName = field_name;
                $('#image-uploader').click();
            }
        }
    });
}
