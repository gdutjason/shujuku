package com.qg.dao;

import com.qg.entity.Action;

import java.util.ArrayList;

/**
 * Created by jason on 16-10-18.
 */
public interface ActionDao {
    /**
     * 获取所有的动作
     * @return
     */
    ArrayList<Action> queryAll();

    /**
     * 新建一个动作
     * @param action
     * @return
     */
    boolean save(Action action);
}
