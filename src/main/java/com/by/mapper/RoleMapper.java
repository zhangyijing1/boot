package com.by.mapper;

import com.by.model.Role;
import com.by.model.RolepermissionVo;
import com.by.model.Rpvo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> query();

    void delete(@Param("arr") List<Integer> arr);


    List<RolepermissionVo> findAll(Map<String, Object> pageMap);


    @Delete("delete from t_rp where role_id=#{roleId}")
    void deleteByrpId(@Param("roleId") Integer roleId);


    void insertrolepermission(@Param("rp") Rpvo rpvo);
    @Delete("delete from r_role where role_id=#{roleId}")
    void delete2(@Param("roleId") Integer roleId);
}