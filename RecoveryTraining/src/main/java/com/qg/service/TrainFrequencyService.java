package com.qg.service;

import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;

import java.util.List;

/**
 *
 * Created by wf on 2016/12/23.
 */
public interface TrainFrequencyService {

    /**
     * 录入车辆班次信息
     * @param trainFrequency
     * @return
     */
    public int insert(TrainFrequency trainFrequency);

    /**
     * 通过班次id查找班次
     * @param id
     * @return
     */
    public TrainFrequency selectById(Integer id);

    /**
     * 查找所有班次
     * @return
     */
    public List<TrainFrequency> selectAll();

    /**
     * 按照终点站查找班次
     * @param name
     * @return
     */
    public List<TrainFrequency> selectByEndStation(String name);

    /**
     * 获取实体类
     */
    public TrainFrequency getEntity(Integer frequencyId);


    /**
     * 获取车次的火车
     */
    public Train getTrain(Integer frequencyId);
}
