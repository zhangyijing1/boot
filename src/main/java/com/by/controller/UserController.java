package com.by.controller;


import com.by.model.*;
import com.by.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by zyj on 2019/7/1.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("users")
    public String login(@PathParam("userName") String userName,@PathParam("userPswd") String userPswd) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(userName,userPswd);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                System.out.println("登陆失败：户名不存在");
            } catch (IncorrectCredentialsException e){
                System.out.println("密码错误");
            } catch (AuthenticationException e){
                System.out.println("登陆失败");
            }
        }
        return "redirect:/user/list";
    }
    @RequestMapping("zc")
    public String zc(){
        return "/zc";
    }
    @RequestMapping("zhuce")
    public String zhuce(User user){
      boolean b= userService.zhuce(user);
      if (b){
          return "redirect:/index.jsp";
      }
        return "redirect:/user/zc";
    }

    @RequestMapping("list")
    public String list() {

        return "/list";
    }


   @ResponseBody
    @RequestMapping("user1")
    public List<User> query1() {
        List<User> list = userService.query1();
        return list;
    }
    @ResponseBody
    @RequestMapping("user2")
    public Map<String,Object> query2(int page,int limit) {
       PageHelper.startPage(page, limit);
      List<User> list1 = userService.query1();
        PageInfo pageInfo = new PageInfo(list1);
       Map<String, Object> map = new HashMap<>();
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
       map.put("data",pageInfo.getList());
     return map;
   }
   @ResponseBody
   @RequestMapping("user3")
   public Map<String,Object> users(Integer page,Integer limit){
       //使用mybatis分页插件，使用方法，在查询全部方法之前， PageHelper.startPage(当前页,每页条数);
       // PageHelper.startPage(page,limit);
       Map<String,Object> pageMap = new HashMap<>();
       pageMap.put("start",(page - 1)*limit);
       pageMap.put("limit",limit);
       List<UserRoleVo> users = userService.findAll(pageMap);

       //获取分页的详细信息
       // PageInfo<UserVO> info = new PageInfo<>(users);

       //按照需要响应的格式，添加数据
       Map<String,Object> map = new HashMap<>();
       map.put("code",0);
       map.put("msg","");
       map.put("count",50);
       map.put("data",users);
       return map;
   }
    @ResponseBody
    @PostMapping("userRole")
    public Map<String, Object> userRole(UrVo urVo) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.userRole(urVo);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @GetMapping("userRol")
    public Set<String> userRol(@RequestParam("userId") Integer userId) {
            Set<String> roles=userService.userRol(userId);
          return roles;


    }
    @RequestMapping("list1")
    public String list1() {
        return "/query";
    }

    @RequestMapping("list2")
    public String list2() {
        return "/layquery";
    }

    @ResponseBody
    @RequestMapping("add")
    public String add(User user) {

        try {
            userService.add(user);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    public Map<String,Object> delete(UserVo ids) {
        Map<String,Object> map2=new HashMap<>();
        try {
            userService.delete(ids);
            map2.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map2.put("error",false);
        }
        return map2;
    }
    @ResponseBody
    @DeleteMapping("user/{id}")
    public Map<String,Object> delete1(@PathVariable("id") Integer userId) {
       Map<String,Object> map= new HashMap<>();
        try {
            userService.delete1(userId);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",false);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("updatepage/{id}")
    public User updatepage(@PathVariable("id") Integer userId){
        User user=userService.byId(userId);
        return user;
    }
    @ResponseBody
    @RequestMapping("update")
    public String update(User user){
        try {
            userService.update(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }
}