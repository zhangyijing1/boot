package com.by.service;

import com.by.model.Permission;
import com.by.model.PermissionVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
public interface PermissionService {
    List<Permission> query1();

    void add1(Permission permission);

    Permission byId(Integer permissionId);

    void update(Permission permission);

    void delete(PermissionVo arr);

    List<Permission> findAll1(Map<String, Object> pageMap);

    void del(Integer permissionId);
}
