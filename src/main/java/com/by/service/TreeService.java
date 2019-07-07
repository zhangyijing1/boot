package com.by.service;

import com.by.model.Tree;
import com.by.model.TreeVo;

import java.util.List;

/**
 * Created by zyj on 2019/7/1.
 */
public interface TreeService {
    List<Tree> selectBypid(Integer id);

    List<TreeVo> query(Integer id);
}
