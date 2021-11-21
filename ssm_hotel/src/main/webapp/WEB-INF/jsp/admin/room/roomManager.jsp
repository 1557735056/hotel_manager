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

    <style>
        .thumbBox{ height:200px; overflow:hidden; border:1px solid #e6e6e6; border-radius:2px; cursor:pointer; position:relative; text-align:center; line-height:200px;width: 210px;}
        .thumbImg{ max-width:100%; max-height:100%; border:none;}
        .thumbBox:after{ position:absolute; width:100%; height:100%;line-height:200px; z-index:-1; text-align:center; font-size:20px; content:"缩略图"; left:0; top:0; color:#9F9F9F;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <%-- 搜索条件 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">房间编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="roomnum" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomtypeid" id="s_roomTypeId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="s_floorId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="s_status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">已入住</option>
                                    <option value="2">打扫中</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
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

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-close"></i>删除</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存房型id -->
                <input type="hidden" name="id">
                <div class="layui-col-md12 layui-col-xs12">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md9 layui-col-xs7">
                            <div class="layui-form-item magt3" style="margin-top: 8px;">
                                <label class="layui-form-label">房间编号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input" name="roomnum" lay-verify="required"  placeholder="请输入房间编号">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间类型</label>
                                <div class="layui-input-block">
                                    <select name="roomtypeid" id="roomtypeid" lay-verify="required">
                                        <option value="">请选择房型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间备注</label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3 layui-col-xs5">
                            <div class="layui-upload-list thumbBox mag0 magt3">
                                <input type="hidden" name="photo" id="photo" value="/statics/images/defaultimg.jpg">
                                <img class="layui-upload-img thumbImg" src="/static/images/defaultimg.jpg">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item magb0">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="floorid" lay-verify="required">
                                    <option value="">请选择楼层</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="status" lay-verify="required">
                                    <option value="">请选择房间状态</option>
                                    <option value="1">已预订</option>
                                    <option value="2">已入住</option>
                                    <option value="3">可预订</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间要求</label>
                        <div class="layui-input-block" >
                            <textarea id="roomrequirement" name="roomrequirement" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间详情</label>
                        <div class="layui-input-block" >
                            <textarea id="roomdesc" name="roomdesc" style="display: none;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center;">
                            <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit" id="doSubmit"><span
                                    class="layui-icon layui-icon-add-1"></span>提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><span
                                    class="layui-icon layui-icon-refresh-1"></span>重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/static/layui/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layer','table','upload','layedit'], function () {
        var $ = layui.jquery,
            form = layui.form,
            upload  =layui.upload,
            layedit = layui.layedit,
        table = layui.table;
        let tableIns =  table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/room/list',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 60, title: '编号', align: "center"},
                {field: 'roomname', minWidth: 150, title: '房间图片', align: "center"},
                {field: 'roomnum', minWidth: 120, title: '房间编号', align: "center"},
                {field: 'typeName', minWidth: 100, title: '房间类型', align: "center",templet:function (d) {
                        return d.typeName;
                    }},
                {field: 'floorName', minWidth: 100, title: '所属楼层', align: "center",templet:function (d) {
                        return d.floorName;
                    }},
                {field: 'statusStr', minWidth: 100, title: '房间状态', align: "center"},
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
        let detailIndex;
        table.on('toolbar(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log("hello");
            if (obj.event === 'add') {
                mainIndex = layer.open({
                    type: 1,
                    title: "添加房间",
                    area: ["800px", "500px"],
                    content: $("#addOrUpdateWindow"),
                    //窗口最大化
                    maximum:true,
                    success: function () {
                        //清空表单数据
                        $("#dataFrm")[0].reset();
                        //添加提交请求
                        url = "/admin/room/addRoom";
                        //重置默认图片,显示图片必须在图片名称前加上/hotel/show
                        $(".thumbImg").attr("src","/static/images/defaultimg.jpg");
                        // //重置图片隐藏的域
                        $("#photo").val("/images/defaultimg.jpg")
                    }
                });
                //设置窗口打开时最大化显示
                layer.full(mainIndex);
                //初始化富文本编辑器
                layedit.set({
                    uploadImage:{
                        url:"/admin/file/uploadFile",
                        type:"post"
                    }
                })
                detailIndex = layedit.build("roomdesc");
                return false;
            }
        });
        upload.render({
            elem: '.thumbImg' //绑定元素
            ,url: '/admin/file/uploadFile', //上传接口
            acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型
            field: 'file',//文件上传的字段值，等同于input标签的name属性值，该值必须与控制器中的方法参数 名一致
            method: "post"//提交方式
            //文件上传成功后的回调函数
            ,done: function(res,index,upload){
                console.log(res+"---"+res.data+"---"+res.data.src)
                //上传完毕回调
                //设置图片回显路径
                $(".thumbImg").attr("src",res.data.src);
                $('.thumbBox').css("background", "#fff");
                // //给图片隐藏域赋值
                $("#photo").val(res.imagePath);
            }
            ,error: function(){
                //请求异常回调
            }
        });
        /**
         * 获取所有房型
         */
        $.get("/admin/roomType/findAll",function(result){
            let html = null;
            for (let i = 0; i < result.length; i++) {
                html +="<option value='"+result[i].id+"'>"+result[i].typename+"</option>";
            }
            //将网页代码追加到下拉列表中
            $("[name = 'roomtypeid']").append(html);
            //重新渲染下拉雷彪
            form.render("select");
        },"json");

        /**
         * 获取所有楼层
         */
        $.get("/admin/floor/findAll",function(result){
            let html = null;
            for (let i = 0; i < result.length; i++) {
                html +="<option value='"+result[i].id+"'>"+result[i].name+"</option>";
            }
            //将网页代码追加到下拉列表中
            $("[name = 'floorid']").append(html);
            //重新渲染下拉雷彪
            form.render("select");
        },"json");
        //监听表单提交事件
        form.on('submit(doSubmit)',function (data){
            console.log(url+"---"+data.field+"---"+$("#dataFrm").serialize());
            //将富文本编辑器的内容同步到文本域textarea中
            layedit.sync(detailIndex);
            $.ajax({
                url:url,
                dataType:"json",
                type: "post",
                //$("#dataFrm").serialize(),一次性获取表单数据,要求表单必须由name属性
                data:$("#dataFrm").serialize(),
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
            //静止页面刷新
            return false;
        });

        //监听行工具栏事件 编辑窗口
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                console.log("edit"+"---"+data);
                mainIndex = layer.open({
                    type: 1,
                    title: "修改房间信息",
                    area: ["800px", "500px"],
                    content: $("#addOrUpdateWindow"),
                    //窗口最大化
                    maximum:true,
                    success: function () {
                        //表单数据回显 //参数1 lay_filter值,参数2 回显的数据
                        form.val("dataFrm",data);
                        //添加提交请求
                        url = "/admin/room/updateRoom";
                    }
                });
                //设置窗口打开时最大化显示
                layer.full(mainIndex);
                //初始化富文本编辑器
                layedit.set({
                    uploadImage:{
                        url:"/admin/file/uploadFile",
                        type:"post"
                    }
                })
                detailIndex = layedit.build("roomdesc");
                return false;
            }

            if (obj.event === 'delete') {
                layer.confirm('确定要删除吗?', {icon: 3, title:'提示'}, function(index){
                    //do something 发送ajax请求删除信息
                    $.ajax({
                        type:'get',
                        url:"/admin/room/deleteRoom",
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
