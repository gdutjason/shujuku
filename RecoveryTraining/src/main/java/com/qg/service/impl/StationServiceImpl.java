package com.qg.service.impl;

import com.qg.dao.StationMapper;
import com.qg.entity.Station;
import com.qg.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 *
 * Created by symon on 16-12-23.
 */
@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

    public Station getStationById(Serializable id) {
        return stationMapper.selectByPrimaryKey((Integer)id);
    }
}
