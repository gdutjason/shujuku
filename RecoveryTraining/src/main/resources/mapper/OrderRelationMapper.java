package com.qg.dao;

import com.qg.entity.OrderRelationKey;

public interface OrderRelationMapper {
    int deleteByPrimaryKey(OrderRelationKey key);

    int insert(OrderRelationKey record);

    int insertSelective(OrderRelationKey record);
}