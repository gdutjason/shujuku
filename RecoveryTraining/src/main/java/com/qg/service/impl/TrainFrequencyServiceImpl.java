package com.qg.service.impl;

import com.qg.dao.TrainFrequencyMapper;
import com.qg.dao.TrainMapper;
import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;
import com.qg.service.TrainFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainFrequencyServiceImpl implements TrainFrequencyService {

    @Autowired
    private TrainFrequencyMapper trainFrequencyMapper;

    @Autowired
    private TrainMapper trainMapper;

    @Override
    public TrainFrequency getEntity(Integer frequencyId) {
        return trainFrequencyMapper.selectByPrimaryKey(frequencyId);
    }

    @Override
    public Train getTrain(Integer frequencyId) {
        TrainFrequency frequency = trainFrequencyMapper.selectByPrimaryKey(frequencyId);
        return trainMapper.selectByPrimaryKey(frequency.getTrainId());
    }
}
