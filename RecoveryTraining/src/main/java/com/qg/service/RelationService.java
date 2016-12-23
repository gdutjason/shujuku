package com.qg.service;

import com.qg.entity.Relation;


/**
 * Created by wf on 2016/12/23.
 */
public interface RelationService {

    /**
     * 根据身份证号搜索联系人
     * @param id
     * @return
     */
    public Relation getRelationById(String id);

    /**
     * 增加联系人
     * @param relation
     * @return
     */
    public int insert(Relation relation);

    /**
     * 根据身份证号码删除联系人
     * @param id
     * @return
     */
    public int delete(String id);

}
