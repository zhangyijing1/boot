package com.by.mapper;

import com.by.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> query();

    void delete(@Param("arr") List<Integer> arr);

    void add(Permission permission);

    List<Permission> query1(Map<String, Object> pageMap);
    @Delete("delete from t_permission where permission_id=#{permissionId}")
    void del(@Param("permissionId") Integer permissionId);
}