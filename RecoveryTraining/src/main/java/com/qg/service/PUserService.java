package com.qg.service;

import com.qg.entity.DUser;
import com.qg.entity.MedicalRecord;
import com.qg.entity.PUser;
import com.qg.entity.RcStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 16-10-13.
 */
public interface PUserService {

    /**
     * 根据病患手机和密码，查询返回病患信息
     * 若不存在返回空，若密码不对返回id=0的病患
     * @param phone 病患手机
     * @param passowrd 病患密码
     * @return
     */
    PUser queryByPassword(String phone , String passowrd);

    /**
     * 拿到病历
     * @param id
     * @return
     */
    ArrayList<MedicalRecord> getMR(int id);

    /**
     * 根据手机号 拿到医生的信息
     * @param phones
     * @return
     */
    Map<String,DUser> getDUserByPhone(ArrayList<String> phones);

    /**
     * 根据病历的id 获取该病历下的所有康复阶段
     * @param mrId
     * @return
     */
    ArrayList<RcStage> getRcStage(int mrId);

    int updatePUser(PUser pUser);
}
