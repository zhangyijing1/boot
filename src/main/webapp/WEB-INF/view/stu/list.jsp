<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/layui/2.4.5/css/layui.css">
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="/webjars/layui/2.4.5/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">0701 八一it</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <shiro:user>
                        <shiro:principal/>
                    </shiro:user>
                </a>
            </li>
            <li class="layui-nav-item"><a href="/user/logout">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item ">
                    <a class="" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <shiro:hasRole name="admin">
                            <a href="javascript:;" class="abc" data_id="yhlb" data_url="/user/list2"
                               data_title="用户列表 ">用户列表</a>
                            </shiro:hasRole>
                        </dd>

                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" >角色管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="abc" data_id="jslb" data_url="/role/list"
                               data_title="角色列表 ">角色列表</a></dd>>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">权限管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="abc" data_id="qxlb" data_url="/per/list"
                               data_title="权限列表 ">权限列表</a></dd>>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="myTabs" lay-allowclose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="11">欢迎页面</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">欢迎光临</div>
            </div>
        </div>

    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
       1902制造
    </div>
</div>
<script>
    layui.use('element', function(){
        var element = layui.element;
        var $ =layui.jquery;
        active = {
            tabAdd: function(title,url,id){
                element.tabAdd('myTabs', {
                    title: title
                    , content: '<iframe style="width:100%;height:100%;position:relative;" src="'+url+'" frameborder="0" scrolling="true" ></iframe>'
                    ,id:id
                })
            }
            ,tabDelete: function(othis){
                element.tabDelete('demo', '44');
                othis.addClass('layui-btn-disabled');
            }
            ,tabChange: function(id){
                //切换到指定Tab项
                element.tabChange('myTabs', id);
            }
        };
    });

    $(".abc").on("click",function () {
        var data_id =$(this).attr("data_id")
        var data_title =$(this).attr("data_title")
        var data_url =$(this).attr("data_url")
        if($(".layui-tab-title li[lay-id]").length <= 0){
            active.tabAdd(data_title,data_url,data_id);
        }else{

            var flag = false;
            $(".layui-tab-title li[lay-id]").each(function(json){
                if($(this).attr("lay-id") == data_id){
                    flag = true;
                }
            })
            if(flag == false){
                active.tabAdd(data_title,data_url,data_id);
            }
        }
        active.tabChange(data_id);

    })


</script>
</body>
</html>