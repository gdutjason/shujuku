package com.qg.service.impl;

import com.qg.dao.RelationMapper;
import com.qg.entity.Relation;
import com.qg.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wf on 2016/12/23.
 */
@Service
public class RelationServiceImpl implements RelationService{

    @Autowired
    private RelationMapper relationMapper;

    public Relation getRelationById(String id) {
        return relationMapper.selectByPrimaryKey(id);
    }

    public int insert(Relation relation) {
        return relationMapper.insert(relation);
    }

    public int delete(String id) {
        return relationMapper.deleteByPrimaryKey(id);
    }

    public List<Relation> selectByUser(Integer userId) {
        return relationMapper.selectByUser(userId);
    }
}
