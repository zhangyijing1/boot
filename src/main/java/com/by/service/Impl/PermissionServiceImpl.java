package com.by.service.Impl;

import com.by.mapper.PermissionMapper;
import com.by.model.Permission;
import com.by.model.PermissionVo;
import com.by.model.Role;
import com.by.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> query1() {
        List<Permission> list=permissionMapper.query();
        return list;
    }

    @Override
    public void add1(Permission permission) {
        permissionMapper.add(permission);

    }

    @Override
    public Permission byId(Integer permissionId) {
        Permission permission=permissionMapper.selectByPrimaryKey(permissionId);
        return permission;
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void delete(PermissionVo arr) {
       permissionMapper.delete(arr.getArr());
    }

    @Override
    public List<Permission> findAll1(Map<String, Object> pageMap) {
      List<Permission> list1=  permissionMapper.query1(pageMap);
        return list1;
    }

    @Override
    public void del(Integer permissionId) {

        permissionMapper.del(permissionId);
    }
}
