package com.qg.service;


import com.qg.entity.Relation;
import com.qg.entity.TrainFrequency;
import com.qg.entity.TrainOrder;
import com.qg.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 订单服务类
 * Created by symon on 16-12-23.
 */
public interface TrainOrderService {

    /**
     * 卖出火车票，返回是否成功
     * @return
     */
    public Map<String, Object> sell(List<String> relationtList,
                                    Integer frequencyId,
                                    Integer userId);

    /**
     * 获取某个用户的全部订单
     */
    public List<TrainOrder> getAllUserOrder(User user);

    /**
     * 获取某个订单中所包含的联系订票人
     */
    public List<Relation> getRelationInOrder(Integer orderId);

    /**
     * 取消订单
     */
    public void refund(Integer orderId);
}
