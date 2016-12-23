package com.qg.dao;

import com.qg.entity.TrainFrequency;

import java.util.List;

public interface TrainFrequencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainFrequency record);

    int insertSelective(TrainFrequency record);

    TrainFrequency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainFrequency record);

    int updateByPrimaryKey(TrainFrequency record);

    List<TrainFrequency> selectAll();

    List<TrainFrequency> selectByEndStation(String name);
}