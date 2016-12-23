package com.qg.service.impl;

import com.qg.dao.OrderRelationMapper;
import com.qg.dao.RelationMapper;
import com.qg.dao.TrainFrequencyMapper;
import com.qg.dao.TrainOrderMapper;
import com.qg.entity.*;
import com.qg.service.TrainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by symon on 16-12-23.
 */
@Service
public class TrainOrderServiceImpl implements TrainOrderService {

    @Autowired
    private TrainFrequencyMapper frequencyMapper;

    @Autowired
    private OrderRelationMapper orMapper;

    @Autowired
    private TrainOrderMapper trainOrderMapper;

    @Autowired
    private RelationMapper relationMapper;


    private static ReentrantLock lock = new ReentrantLock(true);

    @Transactional
    @Rollback
    @Override
    public Map<String, Object> sell(List<String> relationtList,
                                    Integer frequencyId,
                                    Integer userId) {

        Map<String, Object> result = new HashMap<>();
        TrainFrequency frequency = frequencyMapper.selectByPrimaryKey(frequencyId);

        if(new Date().after(frequency.getBeginTime())) {
            result.put("result", false);
            result.put("desc", "已过时");
        } else if(relationtList.size() > frequency.getRemainNum()) {
            result.put("result", false);
            result.put("desc", "已过时");
        } else {
            //提交成为订单

            //更新余票
            lock.lock();
            frequency.setRemainNum(frequency.getRemainNum() - relationtList.size());
            frequencyMapper.updateByPrimaryKey(frequency);
            lock.unlock();

            //插入订单
            int orderId = (int)System.currentTimeMillis();
            TrainOrder trainOrder = new TrainOrder();
            trainOrder.setId(orderId); //这里留个坑
            trainOrder.setCreateTime(new Date());
            trainOrder.setTrainFrequencyId(frequencyId);
            trainOrder.setUserId(userId);
            trainOrderMapper.insert(trainOrder);

            //插入订单-联系人中间表
            for(String relaId : relationtList) {
                OrderRelationKey key = new OrderRelationKey();
                key.setOrderId(orderId);
                key.setRelationId(relaId);
                orMapper.insert(key);
            }
            result.put("result", true);
        }
        return result;
    }

    @Override
    public List<TrainOrder> getAllUserOrder(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getId());
        return trainOrderMapper.selectByParameters(getMybatisMap(map));
    }

    @Override
    public List<Relation> getRelationInOrder(Integer orderId) {
        return relationMapper.getRelationInOrder(orderId);
    }

    @Override
    public void refund(Integer orderId) {
        TrainOrder order = trainOrderMapper.selectByPrimaryKey(orderId);
        trainOrderMapper.deleteByPrimaryKey(orderId);

        lock.lock();
        TrainFrequency frequency = frequencyMapper.selectByPrimaryKey(order.getTrainFrequencyId());
        frequency.setRemainNum(frequency.getRemainNum() + getRelationInOrder(orderId).size());
        frequencyMapper.updateByPrimaryKey(frequency);
        lock.unlock();
    }

    private Map<String, Object> getMybatisMap(Map<String, Object> map) {
        Map<String, Object> mybatisMap = new HashMap<>();
        mybatisMap.put("map", map);
        return mybatisMap;
    }
}
