package com.qg.service;

import com.qg.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 16-10-13.
 */
public interface DUserService {

    /**
     * 根据手机和密码，查询返回信息
     * 若不存在返回空，若密码不对返回id=0的医生
     * @param phone 手机
     * @param passowrd 密码
     * @return
     */
    DUser queryByPassword(String phone , String passowrd);

    /**
     * 注册一个新的医生
     * @param dUser 医生对象
     * @return
     */
    int save(DUser dUser);

    /**
     * 根据医生的id 获取病历
     * @param id
     * @return
     */
    ArrayList<MedicalRecord> getMR(int id);

    /**
     * 保存病历，若没有病历中的病患，要根据实际情况创建一个新的病患
     * @param medicalRecord
     * @return
     */
    int setMR(MedicalRecord medicalRecord);

    /**
     * 根据病历id 删除改病历
     * @param id
     * @return
     */
    int delMR(int id);

    /**
     * 保存一个康复阶段
     * @param rcStage
     * @return
     */
    int setRcStage(RcStage rcStage);

    /**
     * 根据id删除康复阶段
     * @param id
     * @return
     */
    int delRcStage(int id);

    Map<String,PUser> getPUserByPhone(ArrayList<String> phones);

    int updateDUser(DUser dUser);

    ArrayList<Action> getActions();
}
