package com.qg.service.impl;

import com.qg.dao.UserRelationMapper;
import com.qg.entity.UserRelationKey;
import com.qg.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wf on 2016/12/26.
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationMapper userRelationMapper;

    public int insert(UserRelationKey record) {
        return userRelationMapper.insert(record);
    }

}
