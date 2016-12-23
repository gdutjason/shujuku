package com.qg.dao;

import com.qg.entity.Relation;
import com.qg.entity.TrainOrder;

import java.util.List;
import java.util.Map;

public interface TrainOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainOrder record);

    int insertSelective(TrainOrder record);

    TrainOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainOrder record);

    int updateByPrimaryKey(TrainOrder record);

    List<TrainOrder> selectByParameters(Map<String, Object> map);
}