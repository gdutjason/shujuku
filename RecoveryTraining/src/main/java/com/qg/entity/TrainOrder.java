package com.qg.entity;

import java.util.Date;

public class TrainOrder {
    private Integer id;

    private Integer userId;

    private Date createTime;

    private Integer trainFrequencyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTrainFrequencyId() {
        return trainFrequencyId;
    }

    public void setTrainFrequencyId(Integer trainFrequencyId) {
        this.trainFrequencyId = trainFrequencyId;
    }
}