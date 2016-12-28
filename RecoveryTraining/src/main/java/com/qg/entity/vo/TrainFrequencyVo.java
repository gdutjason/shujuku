package com.qg.entity.vo;

import java.util.Date;

/**
 * 班次信息VO类
 * Created by wf on 2016/12/28.
 */
public class TrainFrequencyVo {

    private String beginStationName;

    private String beginTime;

    private String endStationName;

    private String endTime;

    private int maxNum;

    private String trainName;

    public String getBeginStationName() {
        return beginStationName;
    }

    public void setBeginStationName(String beginStationName) {
        this.beginStationName = beginStationName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
}
