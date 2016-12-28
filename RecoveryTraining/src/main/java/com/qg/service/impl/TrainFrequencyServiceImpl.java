package com.qg.service.impl;

import com.qg.dao.TrainFrequencyMapper;
import com.qg.dao.TrainMapper;
import com.qg.entity.Station;
import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;
import com.qg.entity.vo.TrainFrequencyVo;
import com.qg.service.StationService;
import com.qg.service.TrainFrequencyService;
import com.qg.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wf on 2016/12/23.
 */
@Service
public class TrainFrequencyServiceImpl implements TrainFrequencyService {


    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private TrainFrequencyMapper trainFrequencyMapper;

    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;


    @Override
    public TrainFrequency getEntity(Integer frequencyId) {
        return trainFrequencyMapper.selectByPrimaryKey(frequencyId);
    }

    @Override
    public Train getTrain(Integer frequencyId) {
        TrainFrequency frequency = trainFrequencyMapper.selectByPrimaryKey(frequencyId);
        return trainMapper.selectByPrimaryKey(frequency.getTrainId());
    }

    public Map<String, Object> insert(TrainFrequencyVo tfVO) {
        Map<String, Object> result = new HashMap<>();
        TrainFrequency trainFrequency = new TrainFrequency();
        Train train = trainService.selectByName(tfVO.getTrainName());
        if(train == null) {
            train = new Train();
            train.setName(tfVO.getTrainName());
            trainService.insert(train);
        }

        Station s1 =stationService.getStationByName(tfVO.getBeginStationName());
        Station s2 = stationService.getStationByName(tfVO.getEndStationName());
        if(s1 == null) {
            //插入车站并获取id
            s1 = new Station();
            s1.setName(tfVO.getBeginStationName());
            stationService.insert(s1);
        }
        if(s2 == null) {
            s2 = new Station();
            s2.setName(tfVO.getEndStationName());
            stationService.insert(s2);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bTime = null;
        Date eTime = null;
        try {
            bTime = sdf.parse(tfVO.getBeginTime());
            eTime = sdf.parse(tfVO.getEndTime());
        }   catch (Exception e) {
            e.printStackTrace();
        }
        trainFrequency.setBeginTime(bTime);
        trainFrequency.setEndTime(eTime);
        trainFrequency.setMaxNum(tfVO.getMaxNum());
        trainFrequency.setRemainNum(tfVO.getMaxNum());
        trainFrequency.setBeginStationId(s1.getId());
        trainFrequency.setEndStationId(s2.getId());
        trainFrequency.setTrainId(train.getId());
        trainFrequencyMapper.insert(trainFrequency);
        result.put("result", 1);
        return result;
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
