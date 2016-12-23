package com.qg.dao;

import com.qg.entity.TrainFrequency;

public interface TrainFrequencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainFrequency record);

    int insertSelective(TrainFrequency record);

    TrainFrequency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainFrequency record);

    int updateByPrimaryKey(TrainFrequency record);
}