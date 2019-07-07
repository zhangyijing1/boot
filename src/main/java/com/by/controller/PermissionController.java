package com.by.controller;

import com.by.model.Permission;
import com.by.model.PermissionVo;
import com.by.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
@Controller
@RequestMapping("per")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("list1")
    public  String list(){

        return "/permission";
    }
    @RequestMapping("list")
    public String toListPage(){

        return "/laypermission";

    }
    @ResponseBody
    @DeleteMapping("delete1/{id}")
    public Map<String,Object> delete1(@PathVariable("id") Integer permissionId) {
        Map<String,Object> map= new HashMap<>();
        try {
            permissionService.del(permissionId);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("pers")
    public List<Permission> query1() {
        List<Permission> list = permissionService.query1();
        return list;
    }
    @ResponseBody
    @RequestMapping("add")
    public Map<String, Object> add(Permission permission) {
        Map<String, Object> map = new HashMap<>();
        try {
            permissionService.add1(permission);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("delete")
    public String delete(PermissionVo arr) {
        try {
            permissionService.delete(arr);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("updatepage/{id}")
    public Permission updatepage(@PathVariable("id") Integer permissionId){
        Permission permission=permissionService.byId(permissionId);
        return permission;
    }
    @ResponseBody
    @RequestMapping("update")
    public String update(Permission permission) {
        try {
            permissionService.update(permission);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
        @ResponseBody
        @RequestMapping("permission")
        public Map<String,Object> permission(Integer page,Integer limit){
            Map<String,Object> pageMap = new HashMap<>();
            pageMap.put("start",(page - 1)*limit);
            pageMap.put("limit",limit);
            List<Permission> permission = permissionService.findAll1(pageMap);

            pageMap.put("code",0);
            pageMap.put("msg","");
            pageMap.put("count",50);
            pageMap.put("data",permission);
            return pageMap;
         }
}
