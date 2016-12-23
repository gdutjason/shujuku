package com.qg.service.impl;

import com.qg.dao.TrainFrequencyMapper;
import com.qg.dao.TrainMapper;
import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;
import com.qg.service.TrainFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wf on 2016/12/23.
 */
@Service
public class TrainFrequencyServiceImpl implements TrainFrequencyService {


    @Autowired
    private TrainMapper trainMapper;

    @Autowired
    private TrainFrequencyMapper trainFrequencyMapper;

    @Override
    public TrainFrequency getEntity(Integer frequencyId) {
        return trainFrequencyMapper.selectByPrimaryKey(frequencyId);
    }

    @Override
    public Train getTrain(Integer frequencyId) {
        TrainFrequency frequency = trainFrequencyMapper.selectByPrimaryKey(frequencyId);
        return trainMapper.selectByPrimaryKey(frequency.getTrainId());
    }

    public int insert(TrainFrequency trainFrequency) {
        return trainFrequencyMapper.insert(trainFrequency);
    }

    public TrainFrequency selectById(Integer id) {
        return trainFrequencyMapper.selectByPrimaryKey(id);
    }

    public List<TrainFrequency> selectAll() {
        return trainFrequencyMapper.selectAll();
    }

    public List<TrainFrequency> selectByEndStation(String name) {
        return trainFrequencyMapper.selectByEndStation(name);
    }
}
