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
        columnValue: $("#columnValue")

    }

    //删除标签
    component.deleteLabel.click(function () {
        $.delData(component,
            {
                url: '/labelInfoRest/delete'
            });
    });

    component.addWindow.baseDialog({
        title: '添加标签',
        width: 300,
        height: 200,
        url: "/labelInfoRest/saveOrUpdate"
    });

    //增加标签
    component.addBtn.click(function () {
        component.addWindow.dialog("open");
    });

    //标签列表布局
    component.dataGrid.baseDatagrid({
        url: '/labelInfoRest/list',
        method: 'get',
        columns: [
            [
                {field: 'ck', checkbox: true},
                {
                    field: 'id',
                    title: '编号',
                    width: 100
                },
                {
                    field: 'labelName',
                    title: '标签种类',
                    width: 100
                },
                {
                    field: 'labelCode',
                    title: '标签编码',
                    width: 100
                }
            ]
        ]
    });

    //标签搜索
    component.searchBtn.click(function () {
        component.dataGrid.datagrid('reload', {
            columnName: component.columnName.combobox('getValue'),
            columnValue: component.columnValue.textbox('getValue')
        });
    });

    //修改窗口
    component.updateWindow.baseDialog({
        title: '修改标签',
        width: 300,
        height: 200,
        url: "/labelInfoRest/saveOrUpdate"
    });

    //标签修改
    component.updateBtn.click(function () {
        //获取当前选择行
        var rows = component.dataGrid.datagrid('getSelections');
        if ($.isAllowUpdate(component)) {
            //获取数据详情
            var id = rows[0].id;
            $.fillForm({
                url: "/labelInfoRest/findById",
                data: {
                    "po.id": id
                },
                currentForm: component.updateWindowForm,
                success: function (data) {
                    var $inputs = component.updateWindowForm.find("input");
                    for (var i = 0; i < $inputs.length; i++) {
                        var $input = $($inputs[i]);
                        var inputName = $input.attr("name");
                        if (!$.isEmpty(inputName) && !$.isEmpty(data["po"])) {
                            $input.val(data["po"][inputName.split(".")[1]]);
                        }
                    }
                }
            });

            component.updateWindow.dialog("open");
        }
    });


});
