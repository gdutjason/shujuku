package com.qg.service.impl;

import com.qg.dao.*;
import com.qg.entity.*;
import com.qg.hx.Client;
import com.qg.service.DUserService;
import com.qg.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.RecordCreator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jason on 16-10-13.
 */
@Service
public class DUserServiceImpl implements DUserService {

    @Autowired
    DUserDao dUserDao;
    @Autowired
    MedicalRecordDao medicalRecordDao;
    @Autowired
    PUserDao pUserDao;
    @Autowired
    RcStageDao rcStageDao;
    @Autowired
    ActionDao actionDao;

    public DUser queryByPassword(String phone, String passowrd) {
        DUser dUser = dUserDao.queryByPhone(phone);
        if(null == dUser){
            return null;
        }else {
            if (passowrd.equals(dUser.getPassword())){
                return dUser;
            }else {
                dUser.setId(-1);
                return dUser;
            }
        }
    }

    public int save(DUser dUser) {
        DUser dUser1 = Cache.phoneToDUser.get(dUser.getPhone());
        if(dUser1 != null){
            return 2;
        }else {
            boolean b = dUserDao.save(dUser);
            if(b){
                Cache.refreshDUser();//更新医生缓存
                String s = Client.register(dUser.getPhone(),"d");
                if(s.equals("200")){
                    return 1;
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }
    }

    public ArrayList<MedicalRecord> getMR(int id) {
        return medicalRecordDao.queryByDUserId(id);
    }


    public int setMR(MedicalRecord medicalRecord) {
        //第一步先检查是否有该病患，若没有先创建
        String phone = medicalRecord.getPphone();
        PUser pUser = pUserDao.queryByPhone(phone);
        if(pUser == null){
            pUser = new PUser();
            pUser.setAge(medicalRecord.getAge());
            pUser.setPassword(medicalRecord.getPphone().substring(5));
            pUser.setName(medicalRecord.getPname());
            pUser.setBirth(medicalRecord.getBirth());
            pUser.setPhone(medicalRecord.getPphone());
            pUser.setSex(medicalRecord.getSex());
            boolean bool1 = pUserDao.save(pUser);
            if(bool1){
                Cache.refreshPUser();//更新病患缓存
                String s = Client.register(pUser.getPhone(),"p");
                if(s.equals("200")){
                    Client.addFriend(pUser.getPhone(),medicalRecord.getDphone());
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }
        //第二，已经确保存在该病患了，然后就可以创建病历了
        medicalRecord.setPuserId(pUser.getId());
        boolean bool2 = medicalRecordDao.save(medicalRecord);
        if(bool2){
            return 1;
        }else {
            return 2;//以存在病患，但是创建病历失败
        }
    }

    public int delMR(int id) {
        boolean bool = medicalRecordDao.delById(id);
        if(bool){
            return 1;
        }else{
            return 0;
        }
    }

    public int setRcStage(RcStage rcStage) {
        boolean bool = rcStageDao.save(rcStage);
        if (bool){
            Cache.refreshRcStage();//更新康复数据缓存
            return 1;
        }else {
            return 0;
        }
    }

    public int delRcStage(int id) {
        boolean bool = rcStageDao.delById(id);
        if (bool){
            Cache.refreshRcStage();//更新康复阶段缓存
            return 1;
        }else {
            return 0;
        }
    }

    public Map<String, PUser> getPUserByPhone(ArrayList<String> phones) {
        ConcurrentHashMap<String,PUser> phoneToPUser = new ConcurrentHashMap<String, PUser>();
        for (String phone : phones){
            phoneToPUser.put(phone,Cache.phoneToPUser.get(phone));
        }
        return phoneToPUser;
    }

    public int updateDUser(DUser dUser) {
        boolean bool = dUserDao.updateDUser(dUser);
        if(bool){
            Cache.refreshDUser();
            return 1;
        }else {
            return 0;
        }
    }

    public ArrayList<Action> getActions() {
        ArrayList<Action> actions = actionDao.queryAll();
        return actions;
    }

}
