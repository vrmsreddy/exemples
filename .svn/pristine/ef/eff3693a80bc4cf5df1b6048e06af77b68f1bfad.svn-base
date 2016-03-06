require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination, http) {

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
    $(document).on('click', '#p_user_list .J_edit', function () {
        // 设置当前编辑的数据ID
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);

        var J_gender = $(this).data('gender');
        $('input:radio[name="J_gender"][value="'+J_gender+'"]').attr('checked', true);
        
        $('#J_nick').val($(this).data('nick'));
        $('#J_mobile').val($(this).data('mobile'));
        $('#J_address').val($(this).data('address'));
    });

    //更改状态（正常/禁用）
    $(document).on('click', '#p_user_list .J_update', function () {
        console.log('----status----->');
        var userId = $(this).attr('data-id');
        var status = $(this).attr('data-status');
        var url = config.host + '/user/'+ userId +'/update/'+ status ;
        http.post(url, {}, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            showList(currPageNo);
        });
    });
    //重置密码
    $(document).on('click','#p_user_list .J_resetPass', function(){
        console.log('----reset password----->');
        var userId = $(this).attr('data-id');
        http.post(config.host + '/user/'+userId+'/resetPass', {
            'userId':userId
        }, true,function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('随机充值密码发送至手机！');
        });
    });
    //查看收藏的机构
    $(document).on('click','#p_user_list .J_favor', function(){
        var userId = $(this).data('id');
        console.log('--list--favor--:' + userId);
        http.get(config.host + '/user/' + userId + '/favoriteOrgans', {}, true,function (data) {
            if (!data.result || data.result.length <= 0) {
                $('#favor_organ_list').html($('#v_empty_list').html())
                return;
            }
            $('#favor_organ_list').html(template('t:favor_organ_list', {
                list: data.result
            }));
        });
    });
    // 修改管理员类型：内部、外部
    $(document).on('click', '#p_user_list .J_updateType', function () {
        console.log('----userType----->');
        var userId = $(this).data('id');
        var type = $(this).data('type');
        var url = config.host + '/user/'+ userId +'/type/'+ type ;
        http.post(url, {}, true, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            showList(currPageNo);
        });
    });

    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/user/list/', {
            'nick': $('#J_queryNick').val(),
            'mobile': $('#J_queryMobile').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true, function (data) {
            if (data.result.length == 0) {
                showEmptyWarning();
                return;
            }
            $('#p_user_list').html(template('t:p_user_list', {
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
        $('#p_user_list').html(template('t:v_empty_list', {}));
        $('#page').pagination(0,{});
    }
});
