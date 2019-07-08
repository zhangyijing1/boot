package com.by.service;

import com.by.model.Role;
import com.by.model.RoleVo;
import com.by.model.RolepermissionVo;
import com.by.model.Rpvo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zyj on 2019/7/1.
 */
@Service
public interface RoleService {
    List<Role> query1();

    void add(Role role);

    void delete(RoleVo arr);

    Role byId(Integer roleId);

    void update(Role role);

    List<RolepermissionVo> findAll(Map<String, Object> pageMap);

    void rolepermission(Rpvo rpvo);

    void delete2(Integer roleId);

    Map<String, Object> roleper(Integer roleId);
}
