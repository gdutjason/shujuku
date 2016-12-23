package com.qg.service.impl;

import com.qg.dao.OrderRelationMapper;
import com.qg.dao.TrainFrequencyMapper;
import com.qg.dao.TrainOrderMapper;
import com.qg.entity.OrderRelationKey;
import com.qg.entity.TrainFrequency;
import com.qg.entity.TrainOrder;
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
            Date now = new Date();
            TrainOrder trainOrder = new TrainOrder();
            trainOrder.setCreateTime(now);
            trainOrder.setTrainFrequencyId(frequencyId);
            trainOrder.setUserId(userId);
            trainOrderMapper.insert(trainOrder);

            //插入订单-联系人中间表
            Map<String, Date> map = new HashMap<>();
            map.put("create_time", now);
            Map<String, Object> mybatisMap = new HashMap<>();
            mybatisMap.put("map", map);
            Integer orderId = trainOrderMapper.selectByParameters(mybatisMap).get(0).getId();
            for(String relaId : relationtList) {
                OrderRelationKey key = new OrderRelationKey();
                key.setOrderId(orderId);
                key.setRelationId(relaId);
            }
            result.put("result", true);
        }
        return result;
    }
}
