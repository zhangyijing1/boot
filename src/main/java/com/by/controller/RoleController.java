package com.by.controller;

import com.by.model.*;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
   @RequestMapping("list")
    public  String list(){
        return "/layrolelist";
    }
    @ResponseBody
    @RequestMapping("roles")
    public List<Role> query1() {
        List<Role> list = roleService.query1();
        return list;
    }
    @ResponseBody
    @DeleteMapping("role/{id}")
    public Map<String,Object> delete1(@PathVariable("id") Integer roleId) {
        Map<String,Object> map= new HashMap<>();
        try {
            roleService.delete2(roleId);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("role3")
    public Map<String,Object> roles(Integer page,Integer limit){
        Map<String,Object> pageMap = new HashMap<>();
        pageMap.put("start",(page - 1)*limit);
        pageMap.put("limit",limit);
        List<RolepermissionVo> permissions = roleService.findAll(pageMap);
        pageMap.put("code",0);
        pageMap.put("msg","");
        pageMap.put("count",50);
        pageMap.put("data",permissions);
        return pageMap;
    }
    @ResponseBody
    @PostMapping("rolepermission")
    public Map<String, Object> rolepermission(Rpvo rpvo) {
        Map<String, Object> map = new HashMap<>();
        try {
            roleService.rolepermission(rpvo);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("add")
    public Map<String, Object> add(Role role) {
        Map<String, Object> map = new HashMap<>();
        try {
            roleService.add(role);
            map.put("success",true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);

        }
        return map;
    }
    @ResponseBody
    @RequestMapping("delete")
    public String delete(RoleVo arr) {
        try {
            roleService.delete(arr);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    @ResponseBody
    @RequestMapping("updatepage/{id}")
    public Role updatepage(@PathVariable("id") Integer roleId){
        Role role=roleService.byId(roleId);
        return role;
    }
    @ResponseBody
    @RequestMapping("update")
    public String update(Role role){
        try {
            roleService.update(role);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }
}
