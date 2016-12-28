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

    /**
     * 根据名字获取车站
     * @param name
     * @return
     */
    public Station getStationByName(String name);

    /**
     * 添加车站
     * @param station
     * @return
     */
    public int insert(Station station);
}
