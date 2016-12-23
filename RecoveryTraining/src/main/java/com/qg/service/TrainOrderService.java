package com.qg.service;


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
}
