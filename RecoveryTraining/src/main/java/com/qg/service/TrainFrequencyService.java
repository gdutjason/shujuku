package com.qg.service;

import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;

public interface TrainFrequencyService {

    /**
     * 获取实体类
     */
    public TrainFrequency getEntity(Integer frequencyId);


    /**
     * 获取车次的火车
     */
    public Train getTrain(Integer frequencyId);
}
