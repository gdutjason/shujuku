package com.qg.entity;

/**
 * 动作类，描述一个动作
 * Created by jason on 16-10-15.
 */
public class Action {
    private int id;
    private String name;
    private String fileName;//动作对应的文件名字

    public Action(){}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
