package com.qg.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * 病历类
 * Created by jason on 16-10-15.
 */
public class MedicalRecord {
    private int id;
    private int puserId;//所属病人
    private int duserId;//所属医生
    private int age;
    private String pname;//病患名
    private int sex;
    private String pphone;//病患手机
    private Date birth;//病患生日，应该要修改成date
    private String dname;//医生名
    private String dphone;//医生手机
    private String hospital;//医院
    private String department;//科室
    private Date setTime;//建立时间，应该要修改成date
    private String conditions;//病况
    private String allergicDrug;//过敏药物
    private ArrayList<RcStage> rcStage;//康复阶段

    public MedicalRecord(){
        this.rcStage = new ArrayList<RcStage>();
    }



    public String getAllergicDrug() {
        return allergicDrug;
    }

    public void setAllergicDrug(String allergicDrug) {
        this.allergicDrug = allergicDrug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuserId() {
        return puserId;
    }

    public void setPuserId(int puserId) {
        this.puserId = puserId;
    }

    public int getDuserId() {
        return duserId;
    }

    public void setDuserId(int duserId) {
        this.duserId = duserId;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }



    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public ArrayList<RcStage> getRcStage() {
        return rcStage;
    }

    public void setRcStage(ArrayList<RcStage> rcStage) {
        this.rcStage = rcStage;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }
}
