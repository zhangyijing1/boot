package com.by.service.Impl;

import com.by.mapper.PermissionMapper;
import com.by.mapper.RoleMapper;
import com.by.model.*;
import com.by.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> query1() {
        List<Role> list=roleMapper.query();
        return list;
    }

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delete(RoleVo arr) {
        roleMapper.delete(arr.getArr());
    }

    @Override
    public Role byId(Integer roleId) {
      Role role=roleMapper.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<RolepermissionVo> findAll(Map<String, Object> pageMap) {
        List<RolepermissionVo> all = roleMapper.findAll(pageMap);
        return all;
    }

    @Override
    public void rolepermission(Rpvo rpvo) {

      roleMapper.deleteByrpId(rpvo.getRoleId());
        roleMapper.insertrolepermission(rpvo);
    }

    @Override
    public void delete2(Integer roleId) {
        roleMapper.delete2(roleId);
    }

    @Override
    public Map<String, Object> roleper(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        //查询全部的 roles
        List<Permission> permissions =permissionMapper.findAll1();
        //通过id查询role对象

        List<Integer> permissionIds = roleMapper.findRoleIdsByUserId(roleId);

        map.put("permissions",permissions);
        map.put("permissionIds",permissionIds);
        return map;

    }


}
