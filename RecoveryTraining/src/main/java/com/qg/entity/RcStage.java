package com.qg.entity;

/**
 * 康复阶段
 * Created by jason on 16-10-17.
 */
public class RcStage {
    private int id;
    private int mrId;//所属病历
    private int num;//阶段数
    private int actionId;//动作id
    private String actionName;//动作名字
    private float matchValue;//匹配数值
    private int puserId;


    public RcStage(){}

    public int getPuserId() {
        return puserId;
    }

    public void setPuserId(int puserId) {
        this.puserId = puserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMrId() {
        return mrId;
    }

    public void setMrId(int mrId) {
        this.mrId = mrId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public float getMatchValue() {
        return matchValue;
    }

    public void setMatchValue(float matchValue) {
        this.matchValue = matchValue;
    }
}
