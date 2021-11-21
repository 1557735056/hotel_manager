<%--
  Created by IntelliJ IDEA.
  User: 15577
  Date: 2021/11/3
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">楼层名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon layui-icon-search"></i>搜索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i>重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格工具栏 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加 </button>
            </div>
        </script>

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-close"></i>删除</a>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">楼层名称</label>
                    <div class="layui-input-block">
                        <!-- 楼层编号 -->
                        <input type="hidden" name="id">
                        <input type="text" name="name" lay-verify="required" autocomplete="off"
                               placeholder="请输入楼层名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">楼层备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="remark" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/static/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layer','table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        let tableIns =  table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/floor/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 120, title: "楼层编号",align: 'center'},
                {field: 'name', minWidth: 200, title: '楼层名称',align: 'center'},
                {field: 'remark', minWidth: 250, title: '楼层描述',align: 'center'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            //加入此段代码,当最后一页的代码删除完成后
            done:function (res,curr,count){
                //判断当前页码是否大于1并且当前页的数量为0
                if(curr>1&& res.data.length==0){
                    var pageValue = curr-1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page:{curr:pageValue}
                    })
                }
            }
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where:data.field,
                page:{
                    curr: 1
                }
            });
            return false;
        });

        /**
         * toolbar监听事件
         */
        var url;
        var mainIndex;
        table.on('toolbar(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log("hello");
            if (obj.event === 'add') {
                mainIndex = layer.open({
                    type: 1,
                    title: "添加楼层",
                    area: ["800px", "400px"],
                    content: $("#addOrUpdateWindow"),
                    success: function () {
                        //清空表单数据
                        $("#dataFrm")[0].reset();
                        //添加提交请求
                        url = "/admin/floor/addFloor";
                    }
                });
                return false;
            }
        });

        //监听表单提交事件
        form.on('submit(doSubmit)',function (data){
            console.log(url+"---"+data.field);
            $.ajax({
                url:url,
                dataType:"json",
                type: "post",
                data:data.field,
                success:function (result){
                    if(result.success){
                        //刷新数据表格
                        tableIns.reload();
                        //关闭窗口
                        layer.close(mainIndex);
                    }
                    //提示成功信息
                    layer.msg(result.message);
                }
            });
            return false;
        });

        //监听行工具栏事件 编辑窗口
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                console.log("edit"+"---"+data);
                mainIndex = layer.open({
                    type: 1,
                    title: "修改楼层信息",
                    area: ["800px", "400px"],
                    content: $("#addOrUpdateWindow"),
                    success: function () {
                        //表单数据回显 //参数1 lay_filter值,参数2 回显的数据
                        form.val("dataFrm",data);
                        //添加提交请求
                        url = "/admin/floor/updateFloor";
                    }
                });
                return false;
            }

            if (obj.event === 'delete') {
                layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, function(index){
                    //do something 发送ajax请求删除信息
                    $.ajax({
                        type:'get',
                        url:"/admin/floor/deleteById",
                        data:{"id":data.id},
                        dataType: 'json',
                        success:function(result){
                            if(result.success){
                                //数显数据表格
                                tableIns.reload();
                            }else{
                                //提示
                                layer.msg(result.message);
                            }
                        }
                    });
                    layer.close(index);
                });
                return false;
            }
        });
    });
</script>

</body>
</html>
