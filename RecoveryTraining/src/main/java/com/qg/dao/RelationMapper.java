package com.qg.dao;

import com.qg.entity.Relation;

import java.util.List;

public interface RelationMapper {
    int deleteByPrimaryKey(String certNo);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(String certNo);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);

    List<Relation> getRelationInOrder(Integer orderId);

    List<Relation> selectByUser(Integer userId);
}