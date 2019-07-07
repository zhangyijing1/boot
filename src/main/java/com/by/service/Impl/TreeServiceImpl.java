package com.by.service.Impl;

import com.by.mapper.TreeMapper;
import com.by.model.Tree;
import com.by.model.TreeVo;
import com.by.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyj on 2019/7/1.
 */
@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeMapper treeMapper;
    @Override
    public List<Tree> selectBypid(Integer id) {
        List<Tree> trees=treeMapper.selectByid(id);
        return trees;
    }

    @Override
    public List<TreeVo> query(Integer id) {
        List<TreeVo> list=treeMapper.query(id);

        for (int i=0;i<list.size();i++) {
            TreeVo list1 = list.get(i);
            Integer id1 = list1.getTreeId();
            List<TreeVo> list2=treeMapper.query(id1);
            list1.setChildren(list2);
            for (int i1=0;i1<list2.size();i1++){
                TreeVo list3 = list2.get(i1);
                Integer id2 = list3.getTreeId();
                TreeService treeService;
                List<TreeVo> list4 = treeMapper.query(id2);
                list3.setChildren(list4);

            }
        }
        return list;
    }

}
