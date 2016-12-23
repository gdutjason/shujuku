package com.qg.service.impl;

import com.qg.dao.MedicalRecordDao;
import com.qg.dao.PUserDao;
import com.qg.entity.DUser;
import com.qg.entity.MedicalRecord;
import com.qg.entity.PUser;
import com.qg.entity.RcStage;
import com.qg.service.PUserService;
import com.qg.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jason on 16-10-13.
 */
@Service
public class PUserServiceImpl implements PUserService {

    @Autowired
    PUserDao pUserDao;
    @Autowired
    MedicalRecordDao medicalRecordDao;
    /**
     * 根据病患手机和密码，查询返回病患信息
     * 若不存在返回空，若密码不对返回id=0的病患
     * @param phone 病患手机
     * @param passowrd 病患密码
     * @return
     */
    public PUser queryByPassword(String phone, String passowrd) {
        PUser pUser = pUserDao.queryByPhone(phone);
        if(null == pUser){
            return null;
        }else {
            if (passowrd.equals(pUser.getPassword())){
                return pUser;
            }else {
                pUser.setId(-1);
                return pUser;
            }
        }
    }

    public ArrayList<MedicalRecord> getMR(int id) {
        return medicalRecordDao.queryByPUserId(id);
    }

    public Map<String, DUser> getDUserByPhone(ArrayList<String> phones) {
        ConcurrentHashMap<String,DUser> phoneToDUser = new ConcurrentHashMap<String, DUser>();
        for(String phone : phones){
            phoneToDUser.put(phone, Cache.phoneToDUser.get(phone));
        }
        return phoneToDUser;
    }

    public ArrayList<RcStage> getRcStage(int mrId) {
        ArrayList<RcStage> rcStages = Cache.mrIdToRcStages.get(mrId);
        return rcStages;
    }

    public int updatePUser(PUser pUser) {
        boolean bool = pUserDao.updatePUser(pUser);
        if(bool){
            Cache.refreshPUser();
            return 1;
        }else {
            return 0;
        }
    }
}
