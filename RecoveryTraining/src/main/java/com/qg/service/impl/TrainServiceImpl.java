package com.qg.service.impl;

import com.qg.dao.TrainMapper;
import com.qg.entity.Train;
import com.qg.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wf on 2016/12/28.
 */
@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainMapper trainMapper;

    public int insert(Train train) {
        return trainMapper.insert(train);
    }

    public Train selectByName(String name) {
        return trainMapper.selectByName(name);
    }
}
