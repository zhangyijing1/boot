package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by zyj on 2019/7/2.
 */
@Data
public class UserRoleVo {
    private Integer userId;
    private String userName;
    private List<Role> roles;
}
