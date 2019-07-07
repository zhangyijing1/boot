package com.by.mapper;

import com.by.model.Rp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RpMapper {
    int deleteByPrimaryKey(Integer rpId);

    int insert(Rp record);

    int insertSelective(Rp record);

    Rp selectByPrimaryKey(Integer rpId);

    int updateByPrimaryKeySelective(Rp record);

    int updateByPrimaryKey(Rp record);
}