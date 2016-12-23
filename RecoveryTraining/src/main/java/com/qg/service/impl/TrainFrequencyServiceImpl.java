package com.qg.service.impl;

import com.qg.dao.TrainFrequencyMapper;
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
    private TrainFrequencyMapper trainFrequencyMapper;

    public int insert(TrainFrequency trainFrequency) {
        return trainFrequencyMapper.insert(trainFrequency);
    }

    public TrainFrequency selectById(Integer id) {
        return trainFrequencyMapper.selectByPrimaryKey(id);
    }

    public List<TrainFrequency> selectAll() {
        return trainFrequencyMapper.selectAll();
    }

    public List<TrainFrequency> selectByEndStation(Integer endStationId) {
        return trainFrequencyMapper.selectByEndStation(endStationId);
    }

}
