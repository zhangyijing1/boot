<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/2
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/layui/2.4.5/css/layui.css">
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/layui/2.4.5/layui.js"></script>
    <script>
        /*
            toolbar：工具栏
                添加在 table 中，在表格的顶部添加工具栏
                添加在 cols 中，在表格的列中添加工具栏
                toolbar: '#userToolBar'  找 script标签的id
         */
        layui.use(['table','layer','form','jquery'], function(){
            var table = layui.table;
            var form = layui.form;
            $=layui.jquery;
            var layer = layui.layer;

          /*  active = {
                "showRoleByUserId":function(userId){
                    //查询到全部的 role，显示出来
                    //获取到当前用户的 role_id 选中
                    //{'roles':list<Role> ,'roleIds':1,3}
                    $.get("/user/userRol",{'userId':userId},function (data) {
                        $("#roleId").empty();
                        var roles = data.roles;
                        var roleIds = data.roleIds;
                        console.log(data)
                        //清除当前元素中的对象

                        for(var i = 0;i<roles.length;i++){
                            var che = '';
                            //数组.indexOf(xxx)，判断数组中有没有xxx对象，如果没有是-1
                            if(roleIds.indexOf(roles[i].roleId)>-1){//有重复的内容，则应该被选中
                                che = 'checked';
                            }
                            $("#roleId").append('<input type="checkbox" '+che+' name="roles['+i+']" value="'+roles[i].roleId+'" title="'+roles[i].roleName+'"><br>');
                        }
                        form.render(); //更新全部
                    },'json');

                }
            }*/

            //第一个实例
            table.render({
                elem: '#userTable'//对应table载体的id
                ,height: 'full-60'
                ,url: '/role/role3' //加载json数据
                ,page: true //开启分页
                ,toolbar: '#tableToolBar'
                ,cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}//复选框
                    ,{field: 'roleId', title: 'ID', sort: true, fixed: 'left'}
                    ,{field: 'roleName', title: '角色名' }
                    ,{field: 'permissions', title: '权限名' ,templet: function(d){
                            var permissions = d.permissions;
                            var  str = '';
                            for (var i = 0; i < permissions.length; i++) {
                                str += ','+permissions[i].permissionName;
                            }
                            str = str.substring(1);
                            return str;
                        }
                    }
                    ,{fixed: 'right', title:'操作', toolbar: '#userToolBar', width:150}
                ]]
            });
            table.on('tool(userTableFilter)',function(obj){

                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'del'){//说明是删除操作\
                    layer.confirm('真的删除行么', function(index){
                        $.post("/role/role/"+data.roleId,{"_method":"delete"},function(data){
                            if(data.success){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            }else{
                                layer.msg('删除失败！');
                            }
                        },"json");
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }else if(layEvent=== 'edit'){
                    var roleId=data.roleId;
                    $("#roleId").val(roleId);

                    layer.open({
                        type: 1,
                        title:"赋权限",
                        content: $("#giveRole") //这里content是一个普通的String
                        ,area: ['500px', '300px']
                        ,icon: 0
                    });
                }

            });

            table.on('toolbar(userTableFilter)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //
                switch(obj.event){
                    case 'add':
                        layer.msg('添加');
                        break;

                };
                if (layEvent=== 'add'){
                    $("#add")
                    layer.open({
                        type: 1,
                        title:"增加",
                        content: $("#insert") //这里content是一个普通的String
                        ,area: ['500px', '300px']
                        ,icon: 0
                    });
                }
            });
            form.on('submit(giveRoleSubmit)', function(data){
                console.log(data.field);//角色ids

                //ajax赋角色
                $.post("/role/rolepermission",data.field,function(data){
                    if(data.success){

                        table.reload("userTable", {page:{curr:1}});//从第一页加载

                    }else{
                        layer.msg('失败！');
                    }
                    layer.closeAll();
                },"json")

                return false;//防止表单自动提交
            });
            form.on('submit(giveRoleSubmit1)', function(data){

                $.post("/role/add",data.field,function(data){
                    if(data.success){

                        table.reload("userTable", {page:{curr:1}});//从第一页加载

                    }else{
                        layer.msg('失败！');
                    }
                    layer.closeAll();
                },"json")
            return false;//防止表单自动提交
            });

        });



    </script>
    <script type="text/html" id="userToolBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">赋权限</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="tableToolBar">
        <a class="layui-btn layui-btn-xs" lay-event="add">增加</a>
    </script>
</head>
<body>
<table id="userTable" lay-filter="userTableFilter"></table>
<div style="display: none;" id="giveRole">
    <form class="layui-form" action="">
        <input type="hidden" name="roleId" id="roleId"value="roleId">
        <div class="layui-form-item">
            <label class="layui-form-label">权限：</label>
            <div class="layui-input-block" id="permissionId">
                <input type="checkbox" name="permissions[0]" value="1" title="增加"><br>
                <input type="checkbox" name="permissions[1]" value="2"  title="删除"><br>
                <input type="checkbox" name="permissions[2]" value="3"  title="修改"><br>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="giveRoleSubmit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<div style="display: none;" id="insert">
    <form class="layui-form" action="">
        <input type="text" name="roleName">
        <label class="layui-form-label">增加：</label>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="giveRoleSubmit1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
