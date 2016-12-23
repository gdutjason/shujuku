package com.qg.service;

import com.qg.entity.Station;

import java.io.Serializable;

/**
 * 车站服务类
 * Created by symon on 16-12-23.
 */
public interface StationService {

    /**
     * 通过车站id获取车站
     * @param id
     * @return
     */
    public Station getStationById(Serializable id);
}
