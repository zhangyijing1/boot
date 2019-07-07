package com.by.mapper;

import com.by.model.Tree;
import com.by.model.TreeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TreeMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(Tree record);

    int insertSelective(Tree record);

    Tree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(Tree record);

    int updateByPrimaryKey(Tree record);

    List<Tree> selectByid(Integer id);

    List<TreeVo> query(Integer id);
}