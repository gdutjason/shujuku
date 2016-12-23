package com.qg.control;

import com.qg.entity.Action;
import com.qg.entity.MedicalRecord;
import com.qg.entity.PUser;
import com.qg.param.*;
import com.qg.result.*;
import com.qg.service.PUserService;
import com.qg.util.Cache;
import com.qg.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by jason on 16-10-13.
 */

@Controller
@RequestMapping("/PUser")
public class PUserController {
    @Autowired
    PUserService pUserService;

    /**
     * 移动 的 病患登陆
     * @param loginParam
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody LoginParam loginParam){
        LoginResult loginResult = new LoginResult();
        PUser pUser = pUserService.queryByPassword(loginParam.phone,loginParam.password);
        if(pUser != null){
            if(pUser.getId() <= 0)
            {
                loginResult.status = 0;
            }else {
                loginResult.status = 1;
                loginResult.pUser = pUser;
            }
        }else {
            loginResult.status = 2;
        }
        return GsonUtil.gson.toJson(loginResult);
    }

    /**
     * 根据病患的id 查询病历并返回
     * @param param
     * @return
     */
    @RequestMapping("/getMR")
    @ResponseBody
    public String getMR(@RequestBody GetMRParam param){
        GetMRResult result = new GetMRResult();
        result.medicalRecords = (ArrayList<MedicalRecord>) pUserService.getMR(param.pUserId);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 根据电话号码 获取医生信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/getDUserByPhone")
    @ResponseBody
    public String getDUserByPhone(@RequestBody GetDUserByPhoneParam param){
        GetDUserByPhoneResult result = new GetDUserByPhoneResult();
        result.phoneToDUser = pUserService.getDUserByPhone(param.phones);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 根据病历id 获取病历所有康复阶段
     * @param param
     * @return
     */
    @RequestMapping(value = "/getRcStage")
    @ResponseBody
    public String getRcStage(@RequestBody GetRcStageParam param){
        GetRcStageResult result = new GetRcStageResult();
        result.rcStages = pUserService.getRcStage(param.mrId);
        return GsonUtil.gson.toJson(result);
    }


    @RequestMapping(value = "/updatePUser")
    @ResponseBody
    public String updatePUser(@RequestBody UpdatePUserParam param){
        UpdatePUserResult result = new UpdatePUserResult();
        result.status = pUserService.updatePUser(param.pUser);
        return GsonUtil.gson.toJson(result);
    }






    /**
     * pc端病人登陆
     * @param  phone 病人电话
     * @param  password 病人密码
     * @return 病人康复列表-成功，0-失败，2-不能存在该病人
     * @author jason
     * @Time 16-10-17
     */
    @ResponseBody
    @RequestMapping(value = "/PC/login")
    public String pcLogin(@RequestParam("phone") String phone,@RequestParam("password") String password){
        PUser pUser = pUserService.queryByPassword(phone,password);
        if(pUser != null){
            if(pUser.getId() <= 0)
            {
                return "0";
            }else {
                String s = Cache.puserIdToRcStage.get(pUser.getId());
                return s;
            }
        }else {
            return "2";
        }
    }

}
