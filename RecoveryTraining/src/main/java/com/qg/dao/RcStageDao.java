package com.qg.dao;

import com.qg.entity.RcStage;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by jason on 16-10-18.
 */
public interface RcStageDao {

    /**
     * 保存一个康复阶段
     * @param rcStage
     * @return
     */
    boolean save(RcStage rcStage);

    /**
     * 根据id删除一个康复阶段
     * @param id
     * @return
     */
    boolean delById(@Param("id") int id);

    /**
     * 获取所有的康复阶段数据
     * @return
     */
    ArrayList<RcStage> queryAll();

    /**
     * 更新匹配值
     * @param id
     * @param matchValue
     * @return
     */
    boolean updateMatch(@Param("id") int id,@Param("matchValue") float matchValue);
}
