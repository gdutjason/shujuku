package com.qg.entity.vo;

import com.qg.entity.Relation;
import com.qg.entity.TrainFrequency;

import java.util.Date;
import java.util.List;

/**
 * 订单详情vo
 * Created by symon on 16-12-23.
 */
public class OrderVo {

    private Integer id;

    /**
     * 订单时间
     */
    private Date createTime;

    /**
     * 车次
     */
    private TrainFrequency trainFrequency;

    /**
     * 车次名
     */
    private String trainName; //车次名

    /**
     * 购票人组
     */
    private List<Relation> orderRelation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TrainFrequency getTrainFrequency() {
        return trainFrequency;
    }

    public void setTrainFrequency(TrainFrequency trainFrequency) {
        this.trainFrequency = trainFrequency;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public List<Relation> getOrderRelation() {
        return orderRelation;
    }

    public void setOrderRelation(List<Relation> orderRelation) {
        this.orderRelation = orderRelation;
    }
}
