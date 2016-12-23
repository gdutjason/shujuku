package com.qg.dao;

import com.qg.entity.MedicalRecord;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 16-10-18.
 */
public interface MedicalRecordDao {
    /**
     * 根据医生id 获取病历
     * @param duserId 医生的id
     * @return
     */
    ArrayList<MedicalRecord> queryByDUserId(@Param("duserId") int duserId);

    /**
     * 根据病人的id查询病历
     * @param puserId
     * @return
     */
    ArrayList<MedicalRecord> queryByPUserId(@Param("puserId") int puserId);
    /**
     * 保存 一个病历
     * @param medicalRecord 病历对象
     * @return
     */
    boolean save(MedicalRecord medicalRecord);

    /**
     * 根据病历id删除一个病历
     * @param mrId
     * @return
     */
    boolean delById(@Param("mrId") int mrId);

    /**
     * 获取所有的病历
     * @return
     */
    ArrayList<MedicalRecord> queryAll();

}
