<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/6/15
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/themes/icon.css">
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/webjars/github-com-novaeye-jquery-easyui-bower/1.5.0.1/locale/easyui-lang-zh_CN.js"></script>
    <script>
        $(function(){
            $('#dg').datagrid({
                url:'/role/roles',
                singleSelect:false,
                fitColumns:true,
                fit:true,
                pagination:true,
                columns:[[
                    {field:'ck',align:'center',width:50,checkbox:true},
                    {field:'roleId',title:'角色编号',width:100},
                    {field:'roleName',title:'角色姓名',width:100},
                ]],

                toolbar: [{
                    iconCls: 'icon-add',
                    text: "增加",
                    handler: function(){
                        showDialog('add','增加')
                    }
                },'-',{
                    iconCls: 'icon-cancel',
                    text: "删除",
                    handler: function(){
                        deleteemp();
                    }
                },'-',{
                    iconCls: 'icon-edit',
                    text: "修改",
                    handler: function(){
                        updateamp();
                    }
                }]

            });
        })

        function updateamp() {
            showDialog('update','修改');
            var users = $('#dg').datagrid('getSelected');
            if (users==null){
                return"";
            }
            $('#updatefrom').form('load','/role/updatepage/'+users.roleId);
        }



        function showDialog(id,title) {
            $('#'+id).dialog({
                title: title,
                width: 400,
                height: 200,
                closed: false,
                cache: false,
                modal: true
            });
        }
        function submitForm() {
            $.messager.progress();
            $('#ff').form('submit', {
                url: "/role/add",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        $.messager.progress('close');
                    }
                    return isValid;
                },
                success: function (data) {
                    console.log(data)
                    if (data == 'success') {
                        $('#add').dialog("close");
                        $('#dg').datagrid('reload');
                        $('#ff').form("reset");
                    }
                    $.messager.progress('close');
                }
            });

        }
        function updatefrom() {
            $.messager.progress();
            $('#updatefrom').form('submit', {
                url: "/role/update",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (!isValid) {
                        $.messager.progress('close');
                    }
                    return isValid;
                },
                success: function (data) {
                    console.log(data)
                    if (data == 'success') {
                        $('#update').dialog("close");
                        $('#dg').datagrid('reload');
                        $('#updatefrom').form("reset");
                    }
                    $.messager.progress('close');
                }
            });
        }
        function deleteemp() {
            var emps = $('#dg').datagrid('getSelections');
            console.log(emps);
            var arr = {};
            for(var i = 0;i<emps.length;i++){
                arr[i] = emps[i].roleId;
            }
            $.messager.confirm('确认','确定要删除吗？',function(r){
                if (r){
                    $.ajax({
                        url:"/role/delete",
                        type:"delete",
                        data:{
                            "arr":arr
                        },
                        success:function(data){
                            if(data == "success"){
                                $.messager.alert('提示','删除成功');
                            }else{
                                $.messager.alert('提示',"删除失败")
                            }
                            $('#dg').datagrid('reload');
                        }
                    })
                }
            });

        }

    </script>
</head>
<body>

<table id="dg"></table>
<div id="add" style="display: none;">
    <form id="ff" method="post">
        <table cellpadding="5">
            <tr>
                <td>角色编号：</td>
                <td><input class="easyui-textbox" type="text" name="roleId" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>角色姓名：</td>
                <td><input class="easyui-textbox" type="text" name="roleName" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 80px" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 80px" onclick="clearForm()">重置</a>
    </div>

</div>
<div id="update" style="display: none;">
    <form id="updatefrom" method="post">
        <input type="hidden" name="_method" value="put"/>
        <table cellpadding="5">
            <tr>
                <td>角色编号：</td>
                <td><input class="easyui-textbox" type="text" name="roleId" data-options="required:true"></input></td>
            </tr>
            <tr>
                <td>角色姓名：</td>
                <td><input class="easyui-textbox" type="text" name="roleName" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 80px" onclick="updatefrom()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  style="width: 80px" onclick="clearForm()">重置</a>
    </div>

</div>


</body>
</html>
