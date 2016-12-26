package com.qg.dao;

import com.qg.entity.UserRelationKey;

public interface UserRelationMapper {
    int deleteByPrimaryKey(UserRelationKey key);

    int insert(UserRelationKey record);

    int insertSelective(UserRelationKey record);

}