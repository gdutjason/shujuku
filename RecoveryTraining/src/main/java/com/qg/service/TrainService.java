package com.qg.service;

import com.qg.entity.Train;

/**
 * Created by wf on 2016/12/28.
 */
public interface TrainService {

    /**
     * 增加火车
     * @param train
     * @return
     */
    public int insert(Train train);

    /**
     * 根据名字找到火车
     * @param name
     * @return
     */
    public Train selectByName(String name);
}
