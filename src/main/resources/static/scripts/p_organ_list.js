//@sourceURL=p_organ_list.js
require(['config', 'jquery', 'template', 'pagination','validate_extend','http','jquery.bootstrap'], function (config, menubar, template, pagination,validate, http) {


    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

  $("#UserFormBean").validate({

    submitHandler: function(form){
    	if($('#J_lon').val()==""){
    		alert("经度不能为空！");
    		return;
    	}
    	if($('#J_lat').val()==""){
    		alert("纬度不能为空！");
    		return;
    	}


    	if($('#J_phone').val()=="")
    		{
    		alert("机构电话不能为空！");
    		return;
    		}
    	if($('#J_address').val()=="")
		{
		alert("详细地址不能为空！");
		return;
		}
    	if($('#J_share').val()==""){
    		alert("分享数不能为空！");
    		return;
    	}
      //loading('正在提交，请稍等...');
    ~function () {
        http.post(config.host + '/organization/save', {
            'id': currEditId,
            'name': $('#J_name').val(),
            'heat': $('#J_heat').val(),
            'longitude': $('#J_lon').val(),
            'latitude': $('#J_lat').val(),
            'phone': $('#J_phone').val(),
          //  'locationId': $('#J_location :selected').val(),
            conAddress:$('#J_province :selected').text()+"-"+$('#J_city :selected').text()+"-"+$('#J_district :selected').text(),
            'priority': $('#J_priority').val(),
            'provinceId': $('#J_province :selected').val(),
            'cityId': $('#J_city :selected').val(),
            'regionId': $('#J_district :selected').val(),
            'addressDetail': $('#J_address').val(),
            'shareNum': $('#J_share').val(),
            'name': $('#J_name').val(),
            'profile': $('#J_profile').val(),
            'detail': $('#J_detail').val()
        }, true, function (data) {
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
            if(!currEditId){
            	 $('#myModal1').modal('show');
                 currEditId = data.result.id;
                 console.log('----list pic of organ=' + currEditId);
                 http.get(config.host + '/organization/' + currEditId + '/pic/list',{}, true,function (data) {
                     if (data.result.length == 0) {
                         $('#picListBox').html('');
                         return;
                     }
                     $('#picListBox').html(template('t:pic_list', {
                         list: data.result
                     }));
                 })
            }
           
            
        });
    }();
      //form.submit();
    }
  });

    showList(1);

    // 搜索
    $('#J_search').click(function () {
        console.log('---search-----');
        showList(1);
    });
    // 添加
    $('#J_add').click(function () {
    	$("#UserFormBean").get(0).reset(); 
        console.log('---add-----');
        // clear last editId
        currEditId = '';
        
        /*****************/
        renderAreaOptions('J_province', 1, false);
        renderAreaOptions('J_city', $('#J_province').val(), false);
        renderAreaOptions('J_district', $('#J_city').val(), true);
         /**************/
//        
//        renderAreaOptions('/organization/location/opened/city', 'J_city');
//        var cityId = $('#J_city').val();
//        renderAreaOptions('/organization/location/opened/county/' + cityId, 'J_district');
//        var countyId = $('#J_district').val();
//        renderAreaOptions('/organization/location/opened/' + countyId + '/list', 'J_location');
    });
    // 编辑
    $(document).off('click', '#p_organ_list .J_edit').on('click', '#p_organ_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);
        var provinceId = '';
        var cityId = '';
        var countyId = '';
        http.get(config.host + '/organization/'+currEditId,{},false,function(data){
            if (data.code !=200){
                alert(data.msg);
                return;
            }
            locationId = data.result.locationId;

            $('#J_name').val(data.result.name);
            $('#J_heat').val(data.result.heat);
            $('#J_lon').val(data.result.longitude);
            $('#J_lat').val(data.result.latitude);
            $('#J_phone').val(data.result.phone);
            $('#J_address').val(data.result.addressDetail);
            $('#J_share').val(data.result.shareNum);
            $('#J_profile').val(data.result.profile);
            $('#J_detail').val(data.result.detail);
            
            provinceId= data.result.provinceId;
            cityId = data.result.cityId;
            regionId = data.result.regionId;
            console.info([provinceId,cityId,regionId])
            
           
        });
     
        
        renderAreaOptions('J_province', 1, false);
        $('#J_province').find('option[value="' + provinceId + '"]').attr('selected', 'true');
        renderAreaOptions('J_city', provinceId, false);
        $('#J_city').find('option[value="' + cityId + '"]').attr('selected', 'true');
        renderAreaOptions('J_district', cityId, false);
        $('#J_district').find('option[value="' + regionId + '"]').attr('selected', 'true');
//        
//        renderAreaOptions('/organization/location/opened/city', 'J_city');
//        $('#J_city').find('option[value="' + cityId + '"]').attr('selected', 'true');
//
//        renderAreaOptions('/organization/location/opened/county/' + cityId, 'J_district');
//        $('#J_district').find('option[value="' + countyId + '"]').attr('selected', 'true');
//
//        renderAreaOptions('/organization/location/opened/' + countyId + '/list', 'J_location');
//        $('#J_location').find('option[value="' + locationId + '"]').attr('selected', 'true');

    });

    // // 选择城市
    // $('#J_city').on('change', function () {
    //     var cityId = $(this).val();
    //     renderAreaOptions2('/organization/location/opened/county/' + cityId, 'J_district');
    //     var countyId = $('#J_district').val();
    //     renderAreaOptions2('/organization/location/opened/' + countyId + '/list', 'J_location');
    // });

    // // 选择区县
    // $('#J_district').on('change', function () {
    //     renderAreaOptions2('/organization/location/opened/' + $('#J_district').val() + '/list', 'J_location');
    // });

    //$(document).on('click', '#p_organ_list .J_remove', function () {
    //    console.log('----remove----->');
    //    var url = config.host + '/organization/' + $(this).attr('data-id') + '/remove'
    //    http.post(url, {}, function (data) {
    //        if (data.code != 200) {
    //            alert(data.msg);
    //            return;
    //        }
    //        alert('操作成功！');
    //        $('#myModal').modal('hide');
    //        showList(1);
    //    });
    //});

    $(document).off('click','#p_organ_list .J_update').on('click','#p_organ_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/organization/' + id + '/update/' + status, {},true, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').modal('hide');
            showList(currPageNo);
        });
    });

    // // 渲染添加、编辑位置时的下拉框待选项
    // function renderAreaOptions2(url,selectBoxId) {
    //     console.log('---->selectBoxId:' + selectBoxId);
    //     http.get(config.host + url, {}, false, function (data) {
    //             $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
    //         });
    // }

    // $('#J_saveOrgan').click(function () {
    //     post(config.host + '/organization/save', {
    //         'id': currEditId,
    //         'name': $('#J_name').val(),
    //         'heat': $('#J_heat').val(),
    //         'longitude': $('#J_lon').val(),
    //         'latitude': $('#J_lat').val(),
    //         'phone': $('#J_phone').val(),
    //         'locationId': $('#J_location :selected').val(),
    //         'addressDetail': $('#J_address').val(),
    //         'shareNum': $('#J_share').val(),
    //         'name': $('#J_name').val(),
    //         'profile': $('#J_profile').val(),
    //         'detail': $('#J_detail').val()
    //     }, function (data) {
    //         if (data.code != 200) {
    //             alert(data.msg);
    //             return;
    //         }
    //         alert('操作成功！');
    //         $('#myModal').modal('hide');
    //         showList(1);
    //     });
    // });

    //列表
    function showList(pageNo) {
        http.post(config.host + '/organization/list', {
            'name': $('#J_queryName').val(),
            'phone': $('#J_queryPhone').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#p_organ_list').html(template('t:p_organ_list', {
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

    // 管理图片：获取图片列表
    $(document).off('click', '#p_organ_list .J_viewPic').on('click', '#p_organ_list .J_viewPic', function () {
        currEditId = $(this).data('id');
        console.log('----list pic of organ=' + currEditId);
        http.get(config.host + '/organization/' + currEditId + '/pic/list',{}, true,function (data) {
            if (data.result.length == 0) {
                $('#picListBox').html('');
                return;
            }
            $('#picListBox').html(template('t:pic_list', {
                list: data.result
            }));
        })
    });

    // 添加上传图片
    $("#J_uploadOrganPic").unbind('change').change(function(){
        console.log(new FormData($("#uploadForm")[0]))
        try{
     var fileSize = $("#J_uploadOrganPic").get(0).files[0].size;
        if(2*1024*1024<fileSize){
        	alert("请上传小于2M的图片");
        	return ;
        }}catch(e){
        	console.error(e);
        }
        $.ajax({
            url: config.host + "/file/upload",
            type: "POST",
            data: new FormData($("#uploadForm")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
            	if(data.code==500){
          		  alert("上传的图片超过最大2M限制");
          		  return;
          		  
          	  }
                var picList = new Array();
                data.result['path'] = config.host + '/' + data.result['path'];
                // 模板用于显示机构图片记录，此处公用了该模板，需要使用resourceId字段代替id
                data.result['resourceId'] = data.result['id'];
                data.result['id'] = '';
                picList.push(data.result);
                $('#picListBox').append($(template('t:pic_list', {list: picList})));
            },
            error: function () {
                alert("上传失败)");
            }
        });
    });

    // 删除图片
    $(document).off('click', '#picListBox .J_removeOrganPic').on('click', '#picListBox .J_removeOrganPic', function () {
        console.log('--remove pic---');
        var $btn = $(this);
        http.post(config.host + '/organization/pic/remove', {
            resourceId: $(this).data('resourceId')
        }, true,function (data) {
            if (data.code != 200) {
                return;
            }
            $btn.parents('.col-sm-4').addClass('removed');
            $('#picListBox .removed').remove().empty();
        })
    });

    // 保存图片列表
    $(document).off('click', '#J_savePic').on('click', '#J_savePic', function () {
        var params = new Array();
        $('#picListBox .item').each(function (i) {
            console.log(i + '----' + $(this));
            params.push({
                id: $(this).data('id'),
                resourceId: $(this).data('resourceId'),
                isMain: (i === 0) ? 1 : 0,
                organizationId: currEditId,
                priority: i
            });
        });
        if (params.length <= 0) {
            alert('请先上传图片');
            return;
        }
        http.post(config.host + '/organization/pic/save', params, true,function (data) {
            if (data.code != 200) {
                return;
            }
            alert('操作成功！');
            $('#myModal1').modal('hide');
            if(currEditId != ''){
                showList(currPageNo);
            }else{
                showList(1);
            }
        })
    });

    //TODO 图片管理：取消时要检查是否有未保存的图片并提示用户，若确认不保存，需要清除对应的图片

    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#p_organ_list').html(template('t:v_empty_list',{}));
        $('#page').pagination(0,{});
    }
    
    // 选择省、市下拉列表项目时获取下一级地址数据
    $('#J_province').on('change', function () {
        // 获取二级区域数据时设置为同步模式，以实现联动获取三级区域数据
        renderAreaOptions('J_city', $(this).val(), false);
        renderAreaOptions('J_district', $('#J_city').val(), true);
    });

    $('#J_city').on('change', function () {
        renderAreaOptions('J_district', $(this).val(), true);
    });
    // 渲染地址下拉框待选项
    function renderAreaOptions(selectBoxId, parentId, async) {
        console.log('---->selectBoxId:' + selectBoxId + ',parentId:' + parentId);
        http.get( config.host + '/organization/location/areas/' + parentId, {}, async, function (data) {
                $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
            });
    }
});
