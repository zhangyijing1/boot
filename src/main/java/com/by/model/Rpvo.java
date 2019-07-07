package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by zyj on 2019/7/5.
 */
@Data
public class Rpvo {
    private Integer roleId;
    private List<Integer> permissions;
}
