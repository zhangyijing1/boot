package com.by.service;





import com.by.model.UrVo;
import com.by.model.User;
import com.by.model.UserRoleVo;
import com.by.model.UserVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zyj on 2019/7/1.
 */
public interface UserService {



    List<User> query1();

    void add(User user);

    void delete(UserVo ids);


    User byId(Integer userId);

    void update(User user);

    void delete1(Integer userId);

    void userRole(UrVo urVo);


    List<UserRoleVo> findAll(Map<String, Object> pageMap);


    User query(String username);

    boolean zhuce(User user);

    Set<String> selectByrole(String userName);



    Set<String> selectBypermission(String userName);

    Map<String, Object> userRol(Integer userId);
}
