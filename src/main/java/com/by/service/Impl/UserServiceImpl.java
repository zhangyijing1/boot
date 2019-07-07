package com.by.service.Impl;

import com.by.mapper.RoleMapper;
import com.by.mapper.UserMapper;
import com.by.model.*;
import com.by.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zyj on 2019/7/1.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> query1() {
        List<User> list=userMapper.query1();
        return list;
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(UserVo ids) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdIn(ids.getArr());
        userMapper.deleteByExample(example);
    }

    @Override
    public User byId(Integer userId) {

  User user= userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete1(Integer userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public void userRole(UrVo urVo) {
        userMapper.deleteByurId(urVo.getUserId());
        userMapper.insertUserRole(urVo);
    }

    @Override
    public List<UserRoleVo> findAll(Map<String, Object> pageMap) {
        List<UserRoleVo> all = userMapper.findAll(pageMap);

        return all;

    }

    @Override
    public User query(String username) {

       User user= userMapper.query(username);
       return user;
    }

    @Override
    public boolean zhuce(User user) {
        User user1= userMapper.query(user.getUserName());
        if (user1 == null){
            SimpleHash hash = new SimpleHash("MD5", user.getUserPswd(), user.getUserName(), 3);
            user.setUserPswd(hash.toString());
            userMapper.add(user);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> selectByrole(String userName) {
       Set<String> roles= userMapper.selectByrole(userName);
        return roles;
    }

    @Override
    public Set<String> userRol(Integer userId) {
        Set<String> role=userMapper.selectByrol(userId);
        return role;
    }


}
