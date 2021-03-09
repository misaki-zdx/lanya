//获取页面元素
$(function () {
    component = {
        dataGrid: $('#mainDataGrid'),
        deleteLabel: $('#delBtn'),
        addBtn: $("#addBtn"),
        addWindow: $("#addWindow"),
        updateBtn: $("#updateBtn"),
        updateWindow: $("#updateWindow"),
        updateWindowForm: $('#updateWindow form'),
        searchBtn: $("#searchBtn"),
        columnName: $("#columnName"),
        columnValue: $("#columnValue"),
        imgId: ""
    }
});

$(function () {

    //上传图片
    $("#clipArea").photoClip({
        width:533,
        height:200,
        file: "#file",
        ok: "#clipBtn",
        loadStart: function() {
            console.log("照片读取中");
        },
        loadComplete: function() {
            console.log("照片读取完成");
        },
        clipFinish: function(data) {
            $.ajax({
                url:"/addManageRest/upLoadImg",
                method:"post",
                data:{
                    "imgData":data
                },
                dataType:"text",
                success:function(imgUrl){
                    $.ajax({
                        url:"/addManageRest/saveOrUpdate",
                        method:"post",
                        data:{
                            "po.id":component.imgId,
                            "po.imgUrl":imgUrl
                        },
                        dataType:"text",
                        success:function(imgUrl){
                            $('#uploadImg').dialog('close');
                            component.dataGrid.datagrid('reload');
                        }
                    });
                }
            });
        }
    });

    component.dataGrid.baseDatagrid({
        url:'/addManageRest/list',
        method:'get',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'id',title:'id',width:100},
            {field:'imgUrl',title:'图片路径',width:100},
            {field:'imgIntro',title:'广告介绍',width:100},
            {field:'operate',title:'操作',align:'center',width:100,
                formatter:function(value, row, index){
                    //将id隐藏在按钮中，点击按钮就可确定是哪一条数据
                    var btn = '<a target="_blank" id="'+row.id+'" name="searchBtn" class="easyui-linkbutton"></a>';
                    return btn;
                }
            }
        ]],
        onLoadSuccess:function(data){
            $("a[name='searchBtn']").linkbutton({text:'上传广告图片',plain:true,iconCls:'icon-add'});
            $("a[name='searchBtn']").click(function(e){
                $('#uploadImg').dialog('open');
                component.imgId = $(this).attr("id");
                return false;
            });
        }
    });

    //查询操作
    component.searchBtn.click(function(){
        component.dataGrid.datagrid('reload',{
            columnName:component.columnName.combobox('getValue'),
            columnValue:component.columnValue.textbox('getValue')
        });
    });

    //添加操作
    component.addWindow.baseDialog({
        title: '添加广告',
        width: 300,
        height: 200,
        url:"/addManageRest/saveOrUpdate"
    });
    component.addBtn.click(function(){
        component.addWindow.dialog("open");
    });

    //修改操作
    component.updateWindow.baseDialog({
        title: '修改广告',
        width: 300,
        height: 200,
        url:"/addManageRest/saveOrUpdate"
    });
    component.updateBtn.click(function(){
        //获取当前选择行
        var rows = component.dataGrid.datagrid('getSelections');
        if($.isAllowUpdate(component)){
            //获取数据详情
            var id = rows[0].id;
            $.fillForm({
                url:"/addManageRest/findById",
                data:{
                    "po.id":id
                },
                currentForm:component.updateWindowForm,
                success:function(data){
                    var $inputs = component.updateWindowForm.find("input");
                    for(var i=0;i<$inputs.length;i++){
                        var $input = $($inputs[i]);
                        var inputName = $input.attr("name");
                        if(!$.isEmpty(inputName)&&!$.isEmpty(data["po"])){
                            $input.val(data["po"][inputName.split(".")[1]]);
                        }
                    }
                }
            });

            component.updateWindow.dialog("open");
        }
    });

    //删除数据
    component.deleteLabel.click(function(){
        $.delData(component,{
            url:"/addManageRest/deleteByIds"
        });
    });

    //图片上传窗口
    $('#uploadImg').dialog({
        title: '上传图片',
        width: 800,
        height: 500,
        closed: true,
        cache: false,
        modal: true
    });

});

