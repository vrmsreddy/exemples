//@sourceURL=p_course_list.js
require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination, http) {
 var oldName="";
 $("#UserFormBean").validate({
    rules:{
      name2:{
        remote:{
          url:config.host + "/organization/course/checkName" ,
          data:{
            oldName:function(){    return oldName; }
          }
        }
      }

    },
    messages: {
      name2: {remote: "名称已存在"}
    }
  });
    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    // 从机构管理页面”查看课程”跳转过来时，可以取到对应的机构ID
    var currOrganId = $('.right-wrap').data('organId');
    console.log('---course get organId:' + currOrganId);

    showList(1);

    // 搜索
    $('#queryCourseNameList').click(function () {
        console.log('---search-----');
        showList(1);
    });

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/organization/course/list', {
            'name': $('#courseName').val(),
            'organId': currOrganId,
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#course_list').html(template('t:course_list', {
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
    $(document).off('click','#article_list .J_update').on('click','#article_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/article/' + id + '/update/' + status, {}, true,function (data) {
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
    $(document).off('click', '#course_list .J_removeCourse').on('click', '#course_list .J_removeCourse', function () {
        console.log('----remove----->');
        if(confirm('确认删除吗？')){
        var url = config.host + '/organization/course/' + $(this).attr('data-id') + '/remove'
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
     // 添加
    $('#J_add').click(function () {
    $("#UserFormBean").get(0).reset();
     $("#UserFormBean").valid();
        currEditId='';
        console.log('---add-----');
        renderAreaOptions('/organization/category/parent/list', 'J_pCatId','get',{});  // 一级分类
        //二级分类
        renderCategorys();
        renderAreaOptions('/organization/valid/list','J_orgId','get'); // 机构列表
    });
    // 选择父分类时，切换二级分类内容
    $('#J_pCatId').on('change', function () {
        renderCategorys();
        //if ($('#J_catId').children().length <= 0) {
        //    alert('换个分类');
        //}
    });

    /**
     * 渲染二级分类，若内容已存在则无操作
     */
    function renderCategorys() {
        http.post(config.host + '/organization/category/list', {
            'parentCateId': $('#J_pCatId').val(),
            'pageNo': 1,
            'pageSize': 50
        }, false,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            $('#J_catId').html(template('t:v_area_list', {list: data.result}));
        });
    }

    // 渲染添加、编辑分类时的下拉框待选项
    function renderAreaOptions(url,selectBoxId,type) {
        console.log('---->selectBoxId:' + selectBoxId);
        if(type=='get'){
            http.get(config.host + url, {}, false, function (data) {
                $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
            });
        }else{
            http.post(config.host + url, {}, false, function (data) {
                $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
            });
        }
        
    }
    // 保存
    $('#J_saveCourse').click(function () {
      
	if($("#UserFormBean").valid()){
	//fix me 
	 
	if($.isEmptyObject($("#J_pCatId").val())){
	alert("一级分类不能为空");
	return;
	}
	if($.isEmptyObject($("#J_catId").val())){
	alert("二级分类不能为空");
	return;
	}
	
        http.post(config.host + '/organization/course/save', {
            'id': currEditId,
            'name': $('#J_name').val(),
            'age': $('#J_age').val(),
            'cateId': $('#J_catId').val(),
            'orgId': $('#J_orgId').val(),
            'order': $('#J_order').val(),
            'describe': $('#J_describe').val(),
            'oldName':oldName
        }, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').modal('hide');
            $("#UserFormBean").get(0).reset();
            if(currEditId != ''){
                showList(currPageNo);
            }else{
                showList(1);
            }
        });
    }});

    // 编辑
    $(document).off('click', '#course_list .J_edit').on('click', '#course_list .J_edit', function () {
    $("#UserFormBean").valid();
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);
		renderAreaOptions('/organization/category/parent/list', 'J_pCatId','get',{});  // 一级分类
        $('#J_pCatId').find('option[value="' + $(this).data('pcatid') + '"]').attr('selected', 'true');

        renderCategorys(); //二级分类
        $('#J_catId').find('option[value="' + $(this).data('catid') + '"]').attr('selected', 'true');

        renderAreaOptions('/organization/valid/list','J_orgId','get'); // 机构列表
        $('#J_orgId').find('option[value="' + $(this).data('orgid') + '"]').attr('selected', 'true');
		oldName = $(this).data('name');
        $("#J_name").val($(this).data('name'));
        $("#J_age").val($(this).data('age'));
        $("#J_order").val($(this).data('order'));
        $("#J_describe").val($(this).data('describe'));

    });
    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#course_list').html(template('t:v_empty_list',{}));
        $('#page').pagination(0,{});
    }
});
