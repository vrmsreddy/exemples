require(['config', 'jquery', 'template', 'pagination','http'], function (config, menubar, template, pagination,http) {
var oldName="";
  $("#topicStoreBean").validate({
    rules:{
      name:{
        remote:{
          url:config.host + "/evaluation/topicStore/checkName" ,
          data:{
            oldName:function(){   return $("#oldName").val(); }
          }
        }
      }

    },
    messages: {
      name: {remote: "题库名已存在"}
    }
  });


    // 当前编辑的数据ID
    var currEditId;
    var currPageNo = 1;
    var pageSize = 10;

    showList(1);

    // 搜索
    $('#queryTopicStoreList').click(function () {
        console.log('---search-----');
        showList(1);
    });


    //列表
    function showList(pageNo) {
        console.log('--list--pageNo:' + pageNo);
        http.post(config.host + '/evaluation/topicStore/list/', {
            'name': $('#topicStoreName').val(),
            'pageNo': pageNo,
            'pageSize': pageSize
        }, true,function (data) {
            if (data.result.length == 0) {
               showEmptyWarning();
                return;
            }
            $('#topic_store_list').html(template('t:topic_store_list', {
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
    $(document).off('click', '#topic_store_list .J_removeTopicStore').on('click', '#topic_store_list .J_removeTopicStore', function () {
        console.log('----remove----->');
        if(confirm('确认删除吗？')){
            var url = config.host + '/evaluation/topicStore/' + $(this).attr('data-id') + '/remove'
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
    //点击添加
    $("#topicStoreAdd").click(function(){
    	currEditId='';
    	 $("#topicStoreBean :input").val('');
    });
    // 保存
    $('#saveTopicStoreBtn').click(function () {
    	if($("#topicStoreBean").valid()){
        http.post(config.host + '/evaluation/topicStore/save', {
            'id': currEditId,
            'name': $("#topicStoreBean [name='name']").val(),
            'goodDesc': $("#topicStoreBean  [name='goodDesc']").val(),
            'badDesc': $("#topicStoreBean [name='badDesc']").val(),
            'trainPlanName': $("#topicStoreBean [name='trainPlanName'] ").val()

        }, true, function (data) {
            if (data.code != 200) {
                alert(data.msg);
                return;
            }
            alert('操作成功！');
            $('#myModal').modal('hide');
            $("#topicStoreBean").get(0).reset();
            if(currEditId != ''){
                showList(currPageNo);
            }else{
                showList(1);
            }
        });
    }});


    // 编辑
    $(document).off('click', '#topic_store_list .J_edit').on('click', '#topic_store_list .J_edit', function () {
        // 设置当前编辑的数据ID
        $("#topicStoreBean").get(0).reset();
        $("#topicStoreBean").valid();
        $('#myModalLabel').html("编辑题库");
        currEditId = $(this).data('id');
        console.log('---edit----->'+currEditId);
        oldName=$(this).data('name');
        $("#oldName").val($(this).data('name'));
        $("#topicStoreBean [name='name']").val($(this).data('name'));
        $("#topicStoreBean  [name='goodDesc']").val($(this).data('good'));
        $("#topicStoreBean [name='badDesc']").text($(this).data('bad'));
        $("#topicStoreBean [name='trainPlanName'] ").val($(this).data('plan'));

    });
    //展示列表为空的时候的提示信息
    function showEmptyWarning() {
        $('#topic_store_list').html(template('t:v_empty_list', {}));
        $('#page').pagination(0,{});
    }

});
