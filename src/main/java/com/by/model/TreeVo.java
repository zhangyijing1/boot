package com.by.model;

import lombok.Data;

import java.util.List;

/**
 * Created by zyj on 2019/7/1.
 */

    @Data
    public class TreeVo extends Tree{
        private List<com.by.model.TreeVo> children;


    }


