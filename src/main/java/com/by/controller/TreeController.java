package com.by.controller;

import com.by.model.Tree;
import com.by.model.TreeVo;
import com.by.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zyj on 2019/7/1.
 */
@Controller
@RequestMapping("tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @RequestMapping("trees")
    public List<Tree> queryAll(@RequestParam(defaultValue = "0", required = false) Integer id) {
        List<Tree> trees = treeService.selectBypid(id);
        return trees;
    }

    @ResponseBody
    @RequestMapping("trees1")
    public List<TreeVo> query() {
        Integer id = 0;
        List<TreeVo> list = treeService.query(id);

        return list;
    }
}