package com.qg.control;

import com.qg.entity.DUser;
import com.qg.param.*;
import com.qg.result.*;
import com.qg.service.DUserService;
import com.qg.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by jason on 16-10-13.
 */

@Controller
@RequestMapping("/DUser")
public class DUserController {

    @Autowired
    DUserService  dUserService;

    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(){
        return "this is a test";
    }

    /**
     * 移动端 医生登陆接口
     * @param loginParam
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestBody LoginParam loginParam){
        LoginResult loginResult = new LoginResult();
        System.out.println(loginParam.phone+"   "+loginParam.password);
        DUser dUser = dUserService.queryByPassword(loginParam.phone,loginParam.password);
        if(dUser != null){
            if(dUser.getId() <= 0)
            {
                loginResult.status = 0;
            }else {
                loginResult.status = 1;
                loginResult.dUser = dUser;
            }
        }else {
            loginResult.status = 2;
        }
        return GsonUtil.gson.toJson(loginResult);
    }

    /**
     * 移动端注册医生
     * @param param 传入的医生对象
     * @return 返回一个状态，是否成功注册
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(@RequestBody RegisterParam param){

        RegisterResult result = new RegisterResult();
        result.status = dUserService.save(param.dUser);
        return  GsonUtil.gson.toJson(result);
    }

    /**
     * 移动段获取病历列表，根据医生的id
     * @param param
     * @return
     */
    @RequestMapping(value = "/getMR")
    @ResponseBody
    public String getMR(@RequestBody GetMRParam param){
        GetMRResult result = new GetMRResult();
        result.medicalRecords =  dUserService.getMR(param.dUserId);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 移动段新建病历，若没有对应病人资料，新建病人
     * @param
     * @return
     */
    @RequestMapping(value = "/setMR")
    @ResponseBody
    public String setMR(HttpServletRequest request){
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = 0;
            try {
                readlen = request.getInputStream().read(buffer, i,
                        contentLength - i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        String con = null;
        try {
             con =  new String(buffer, charEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SetMRParam param = GsonUtil.gson.fromJson(con,SetMRParam.class);
        SetMRResult result = new SetMRResult();
        result.status = dUserService.setMR(param.medicalRecord);
        return  GsonUtil.gson.toJson(result);
    }

    /**
     * 删除病历，根据病历id
     * @param param 存有一个id
     * @return 返回字段，1-成功，0-失败
     */
    @RequestMapping(value = "/delMR")
    @ResponseBody
    public String delMR(@RequestBody DelMRParam param){
        DelMRResult result = new DelMRResult();
        result.status = dUserService.delMR(param.id);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 新建一个康复阶段,包括动作名称
     * @param param
     * @return
     */
    @RequestMapping(value = "/setRcStage")
    @ResponseBody
    public String setRcStage(@RequestBody SetRcStageParam param){
        SetRcStageResult result = new SetRcStageResult();
        result.status = dUserService.setRcStage(param.rcStage);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 根据id删除一个康复阶段
     * @param param
     * @return
     */
    @RequestMapping(value = "/delRcStage")
    @ResponseBody
    public String delRcStage(@RequestBody DelRcStageParam param){
        DelRcStageResult result = new DelRcStageResult();
        result.status = dUserService.delRcStage(param.id);
        return GsonUtil.gson.toJson(result);
    }

    /**
     * 根据手机号 拿到病人信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/getPUserByPhone")
    @ResponseBody
    public String getPUserByPhone(@RequestBody GetPUserByPhoneParam param){
        GetPUserByPhoneResult result = new GetPUserByPhoneResult();
        result.phoneToPUser = dUserService.getPUserByPhone(param.phones);
        return GsonUtil.gson.toJson(result);
    }

    @RequestMapping(value = "getActions")
    @ResponseBody
    public String getActions(){
        GetActionsResult result = new GetActionsResult();
        result.actions = dUserService.getActions();
        return GsonUtil.gson.toJson(result);

    }


    @RequestMapping(value = "/updateDUser")
    @ResponseBody
    public String updateDUser(@RequestBody UpdateDUserparam param){
        UpdateDUserResult result = new UpdateDUserResult();
        result.status = dUserService.updateDUser(param.dUser);
        return  GsonUtil.gson.toJson(result);
    }





    /**
     * pc端医生用户登陆
     * @param phone 用户名字
     * @param password 用户密码
     * @return 1-成功，0-失败，2-不存在该用户
     * @author jason
     * @Time  16-10-17
     */
    @ResponseBody
    @RequestMapping(value = "/PC/login")
    public int pcLogin(@RequestParam("phone") String phone,@RequestParam("password") String password){
        System.out.println(phone+"   "+password);
        DUser dUser = dUserService.queryByPassword(phone,password);
        System.out.println(GsonUtil.gson.toJson(dUser));
        if(dUser != null){
            if(dUser.getId() <= 0)
            {
               return 0;
            }else {
                return 1;
            }
        }else {
            return 2;
        }
    }


}
