package com.by.mapper;

import com.by.model.Ur;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrMapper {
    int deleteByPrimaryKey(Integer urId);

    int insert(Ur record);

    int insertSelective(Ur record);

    Ur selectByPrimaryKey(Integer urId);

    int updateByPrimaryKeySelective(Ur record);

    int updateByPrimaryKey(Ur record);
}