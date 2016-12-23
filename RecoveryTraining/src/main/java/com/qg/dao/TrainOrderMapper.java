package com.qg.dao;

import com.qg.entity.TrainOrder;

public interface TrainOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainOrder record);

    int insertSelective(TrainOrder record);

    TrainOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainOrder record);

    int updateByPrimaryKey(TrainOrder record);
}