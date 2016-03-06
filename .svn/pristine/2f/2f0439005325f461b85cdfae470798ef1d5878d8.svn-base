require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination,http) {

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
    // 添加
    $('#J_add').click(function () {
        console.log('---add-----');
        // clear last editId
        currEditId = '';
        renderAreaOptions('J_province', 1, false);
        renderAreaOptions('J_city', $('#J_province').val(), false);
        renderAreaOptions('J_district', $('#J_city').val(), true);
    });
    // 编辑
    $(document).off('click', '#p_location_list .J_edit').on('click', '#p_location_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);

        // 分别取三级列表，然后将当前值设置为默认选中项目
        renderAreaOptions('J_province', 1, false);
        var oldProvinceId = $(this).data('provinceId');
        $('#J_province').find('option[value="' + oldProvinceId + '"]').attr('selected', 'true');

        renderAreaOptions('J_city', oldProvinceId, false);
        var oldCityId = $(this).data('cityId');
        $('#J_city').find('option[value="' + oldCityId + '"]').attr('selected', 'true');

        renderAreaOptions('J_district', oldCityId, false);
        var oldDistrictId = $(this).data('districtId')
        console.log('->oldDistrictId:' + oldDistrictId);
        $('#J_district').find('option[value="' + oldDistrictId + '"]').attr('selected', 'true');

        $('#J_name').val($(this).data('name'));
        $('#J_priority').val($(this).data('priority'));
    });

    $(document).off('click', '#p_location_list .J_removeLoc').on('click', '#p_location_list .J_removeLoc', function () {
        console.log('----remove----->');
        if(confirm('确认删除吗？')){
        var url = config.host + '/organization/location/' + $(this).attr('data-id') + '/remove'
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

    $(document).off('click','#p_location_list .J_update').on('click','#p_location_list .J_update', function(){
        console.log('----update----->');
        var id = $(this).attr('data-id');
        var status= $(this).attr('data-status');
        http.post(config.host + '/organization/location/' + id + '/update/' + status, {}, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').hide();
            showList(currPageNo);
        });
    });

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
        console.log('---->selectBoxId:' + selectBoxId + ',parentId:' + parentId)
        http.get(config.host + '/organization/location/areas/' + parentId, {}, false, function (data) {
                $('#' + selectBoxId).html(template('t:v_area_list', {list: data.result}));
            });
    }

    // 保存
    $('#J_saveLoc').click(function () {
    	if($("#J_editForm").valid()){
        http.post(config.host + '/organization/location/save', {
            'id': currEditId,
            'city': $('#J_city :selected').text(),
            'county': $('#J_district :selected').text(),
            'name': $('#J_name').val(),
            'priority': $('#J_priority').val(),
            'provinceId': $('#J_province :selected').val(),
            'cityId': $('#J_city :selected').val(),
            'regionId': $('#J_district :selected').val()
        }, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').modal('hide');
            $("#J_editForm").get(0).reset();
            if(currEditId != ''){
                showList(currPageNo);
            }else{
                showList(1);
            }
        });
    }});

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/organization/location/list/', {
            'name': $('#J_queryName').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#p_location_list').html(template('t:p_location_list', {
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
        $('#p_location_list').html(template('t:v_empty_list', {}));
        $('#page').pagination(0,{});
    }
});
