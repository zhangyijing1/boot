package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by zyj on 2019/7/5.
 */
@Data
public class RolepermissionVo {
    private Integer roleId;
    private String roleName;
    private List<Permission> permissions;
}
