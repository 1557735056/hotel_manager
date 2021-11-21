<%--
  Created by IntelliJ IDEA.
  User: 15577
  Date: 2021/11/3
  Time: 15:15
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/lib/layui-v2.5.5/css/layui.css"
          media="all">
<%--    添加layui-dtree的样式--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui_ext/dtree/dtree.css" media="all">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/public.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui_ext/dtree/font/dtreefont.css" media="all">
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
                            <label class="layui-form-label">角色名称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roleName" autocomplete="off" class="layui-input">
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
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="grantMenu"><i class="layui-icon layui-icon-edit"></i>分配菜单</a>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <input type="hidden" name="id"/>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <!-- 角色编号 -->
                        <input type="hidden" name="id">
                        <input type="text" name="roleName" lay-verify="required" autocomplete="off"
                               placeholder="请输入角色名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色备注</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" name="roleDesc" id="roleDesc"></textarea>
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

        <!-- 分配菜单的弹出层 开始 -->
        <div style="display: none;" id="selectRoleMenuDiv">
            <ul id="roleTree" class="dtree" data-id="0"></ul>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.extend({
        dtree:"${pageContext.request.contextPath}/static/layui_ext/dtree/dtree" //脚本文件的路径

    }).use(['form', 'table', 'laydate', 'jquery','layer','dtree'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
            dtree = layui.dtree,
            table = layui.table;

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/role/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '角色编号', align: "center"},
                {field: 'roleName', minWidth: 150, title: '角色名称', align: "center"},
                {field: 'roleDesc', minWidth: 200, title: '角色描述', align: "center"},
                {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length == 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });
        //监听模糊查询操作
        form.on('submit(data-search-btn)', function (data) {
            console.log(data.field)
            //执行搜索重载
            tableIns.reload({
                where:data.field,
                page:{
                    curr: 1
                }
            });
            return false;
        });
        //监听头部工具栏事件
        //toolbar是头部工具栏
        //currentTableFilter是表格lay-filter过滤器的值
        let url;
        let mainIndex;
        table.on("toolbar(currentTableFilter)",function (obj) {
            switch (obj.event){
                case "add":
                    openAddWindow();//打开添加窗口
                    break;
            }
        });
        //监听行工具栏事件
        function openGrantMenuWindow(data)
        {
            mainIndex = layer.open({
                type: 1,
                title: "分配[<font color='red'>"+data.roleName+"</font>]菜单窗口",
                area: ["400px", "550px"],
                content: $("#selectRoleMenuDiv"),
                success: function () {
                    //渲染dtree组件
                    dtree.render({
                        elem:'#roleTree',//绑定ul标签的id属性赋值
                        url:'/admin/role/initMenuTree/?roleId='+data.id,//请求地址
                        dataStyle: "layuiStyle",  //使用layui风格的数据格式
                        dataFormat: "list",  //配置data的风格为list
                        response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
                        checkbar:true,
                        checkbarType:"all"
                    })
                },
                btn: ['确认', '返回'],
                yes: function(index, layero){
                    //获取选中复选框的值
                    let params = dtree.getCheckbarNodesParam("roleTree");
                    //判断是否有选中复选框
                    if(params.length>0){
                        //发送Ajax请求保存选中菜单的ID和roleId
                        let idArr = [];
                        //循环获取选中的值
                        for (let i = 0; i < params.length; i++) {
                            idArr.push(params[i].nodeId);//nodeId是选中的树节点值,固定是不能修改的
                        }
                        //将字符串转换成字符串 ids 选中的菜单id值
                        let ids = idArr.join(",");
                        //发送ajx请求
                        $.post("/admin/role/saveRoleMenu",{"ids":ids,"roleId":data.id},function (result) {
                            layer.msg(result.message);
                        })
                    }else{
                        //请选择要分配的菜单
                        layer.msg("请选择要分配的菜单");
                    }
                },
                btn2: function(index, layero){

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        }
        function openAddWindow(){
            mainIndex = layer.open({
                type: 1,
                title: "添加角色",
                area: ["800px", "400px"],
                content: $("#addOrUpdateWindow"),
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加提交请求
                    url = "/admin/role/addRole";
                }
            });
        }
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
            console.log("edit"+"---"+data);
            if (obj.event === 'edit') {
                mainIndex = layer.open({
                    type: 1,
                    title: "修改角色",
                    area: ["800px", "400px"],
                    content: $("#addOrUpdateWindow"),
                    success: function () {
                        //表单数据回显 //参数1 lay_filter值,参数2 回显的数据
                        form.val("dataFrm",data);
                        //添加提交请求
                        url = "/admin/role/updateRole";
                    }
                });
                return false;
            }
            if (obj.event === 'delete') {
                //判断当前部门下是否有员工
                $.ajax({
                    type:'get',
                    url:"/admin/role/checkRoleHasEmployee",
                    data:{"id":data.id},
                    dataType: 'json',
                    success:function(result){
                        console.log(result +"-=----"+result.exists)
                        if(result.exists){
                            //提示用户无法删除
                            layer.msg(result.message);
                        }else{
                            //提示用户是否删除该部门
                            layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, function(index){
                                //do something 发送ajax请求删除信息
                                $.ajax({
                                    type:'get',
                                    url:"/admin/role/deleteById",
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
                        }
                    }
                })
                return false;
            }
            if (obj.event === 'grantMenu') {
                openGrantMenuWindow(data);
            }
        });


    });
</script>

</body>
</html>

