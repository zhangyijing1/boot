<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/2
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

            var active = {
                "showRoleByUserId":function(userId){

                    //查询到全部的 role，显示出来
                    //获取到当前用户的 role_id 选中
                    //{'roles':list<Role> ,'roleIds':1,3}
                    $.get("/user/userRol",{'userId':userId},function (data) {
                        console.log(data);
                        var roles = data.roles;
                        var roleIds = data.roleIds;
                        console.log(roles);
                        console.log(roleIds);

                        $("#roleIds").empty();//清除当前元素中的对象

                        for(var i = 0;i<roles.length;i++){
                            var che = '';
                            //数组.indexOf(xxx)，判断数组中有没有xxx对象，如果没有是-1
                            if(roleIds.indexOf(roles[i].roleId)>-1){//有重复的内容，则应该被选中
                                che = 'checked';
                            }
                            $("#roleIds").append('<input type="checkbox" '+che+' name="roles['+i+']" value="'+roles[i].roleId+'" title="'+roles[i].roleName+'"><br>');
                        }
                        form.render(); //更新全部
                    },'json');

                }
            }
            //第一个实例
            table.render({
                elem: '#userTable'//对应table载体的id
                ,height: 'full-60'
                ,url: '/user/user3' //加载json数据
                ,page: true //开启分页
                ,toolbar: '#tableToolBar'
                ,cols: [[ //表头
                    {type: 'checkbox', fixed: 'left'}//复选框
                    ,{field: 'userId', title: 'ID', sort: true, fixed: 'left'}
                    ,{field: 'userName', title: '用户名' }
                    ,{field: 'roles', title: '角色名' ,templet: function(d){
                            var roles = d.roles;
                            var  str = '';
                            for (var i = 0; i < roles.length; i++) {
                                str += ','+roles[i].roleName;
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
                        $.post("/user/user/"+data.userId,{"_method":"delete"},function(data){
                            if(data.success){
                                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            }else{
                                layer.msg('删除失败！');
                            }
                        },"json");
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }else if(layEvent === 'giveRole'){
                    var userId = data.userId;
                    $("#userId").val(userId);

                    //动态查询角色，加载数据，并选中
                    active.showRoleByUserId(userId);

                    layer.open({
                        type: 1,
                        title:"赋角色",
                        content: $("#giveRole") //这里content是一个普通的String
                        ,area: ['500px', '300px']
                        ,icon: 0
                    });
                }

            });



            form.on('submit(giveRoleSubmit)', function(data){
                console.log(data.field);//角色ids

                //ajax赋角色
                $.post("/user/userRole",data.field,function(data){
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
        <a class="layui-btn layui-btn-xs" lay-event="giveRole">赋角色</a>
        <shiro:hasPermission name="/user/delete">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </shiro:hasPermission>
    </script>

</head>
<body>
<table id="userTable" lay-filter="userTableFilter"></table>
<div style="display: none;" id="giveRole">
    <form class="layui-form" action="">
        <input type="hidden" name="userId" id="userId"value="userId">
        <div class="layui-form-item">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-block" id="roleIds">
               <%-- <input type="checkbox" name="roles[0]" value="1" title="admin"><br>
                <input type="checkbox" name="roles[1]" value="2"  title="youke"><br>
                <input type="checkbox" name="roles[2]" value="3"  title="vip"><br>--%>
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

</body>
</html>
