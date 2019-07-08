package com.by.mapper;

import com.by.model.UrVo;
import com.by.model.User;
import com.by.model.UserExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.by.model.UserRoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(Integer record);



    List<User> query1();

    void add(User user);

    void update(User user);
    @Delete("delete from t_ur where user_id=#{userId}")
    void deleteByurId(@Param("userId") Integer userId);

    void insertUserRole(@Param("ur") UrVo urVo);


    List<UserRoleVo> findAll(Map<String, Object> pageMap);


    User query(String username);


    Set<String> selectByrole(@Param("userName") String userName);

    Set<String> selectByrol(@Param("userId") Integer userId);

    Set<String> selectBypermission(@Param("userName") String userName);

    List<Integer> findRoleIdsByUserId(Integer userId);
}