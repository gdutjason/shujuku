package com.qg.control;

import com.qg.dao.ActionDao;
import com.qg.dao.RcStageDao;
import com.qg.entity.Action;
import com.qg.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by jason on 16-10-17.
 */
@Controller
@RequestMapping(value = "actionSys")
public class ActionSysController {
    private static String mypath = "/home/ubuntu/uploadFile";
    //private static String mypath ="/home/jason/jason/myfile/uploadFile";

    @Autowired
    ActionDao actionDao;
    @Autowired
    RcStageDao rcStageDao;

    /**
     * 更新康复阶段的匹配数值，需要自己分割字符串
     * @param phone 病人手机号
     * @param rcStage 康复数据，string形式
     * @return 1-成功,0-失败
     * @author jason
     * @Time 16-10-17
     */
    @ResponseBody
    @RequestMapping(value = "setRcStage")
    public int setRcStage(@RequestParam("phone") String phone, @RequestParam("rcStage") String rcStage) {
        String[] strings = rcStage.split(",");
        for(int i=0; i<strings.length; i=i+2){
            rcStageDao.updateMatch(Integer.parseInt(strings[i]),Float.parseFloat(strings[i+1]));
        }
        return 1;
    }

    /**
     * 文件上传接口，主要处理上传的动作文件
     * 上传的文件并不区别类型
     * @param file    文件集合，可以处理多文件上传，不严格区分类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadAction")
    @ResponseBody
    public String upload(
            @RequestParam("file") MultipartFile[] file,
            HttpServletRequest request) {
        Action action = new Action();
        //获取文件 存储位置
        //String realPath = new String("/home/ubuntu/uploadFile");
        String realPath = mypath;
        File pathFile = new File(realPath);
        if (!pathFile.exists()) {
            //文件夹不存 创建文件
            pathFile.mkdirs();
        }
        //上传一个新的动作，需要对比之前是否有这类动作，若没有需要添加，若有返回对应错误
        for (MultipartFile f : file) {
            String fileName = f.getOriginalFilename();
            action.setName(fileName.substring(0,fileName.lastIndexOf(".")));
            action.setFileName(fileName);
            Action a  = Cache.nameToAction.get(action.getName());
            if (a == null){
                actionDao.save(action);
                Cache.refreshAction();
            }else {
                return "0";
            }
            System.out.println("文件类型：" + f.getContentType());
            System.out.println("文件名称：" + fileName);
            System.out.println("文件大小:" + f.getSize());
            System.out.println("存放路径:" + realPath + "/" + fileName);
            System.out.println(".................................................");
            //将文件copy上传到服务器
            try {
                f.transferTo(new File(realPath + "/" + f.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                return "0";
            }
        }
        return "1";
    }

    @RequestMapping(value = "/downloadAction")
    public String download(HttpServletRequest request,@RequestHeader("actionName") String name,
                           HttpServletResponse response) {
        //String fileName = request.getHeader("actionName");
        //String fileName = "/ceshi.dll";
        String fileName = null;
        try {
            fileName = new String(name.getBytes("ISO-8859-1"),"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
            //String path = new String("/home/ubuntu/uploadFile");
            String path = mypath;
            InputStream inputStream = new FileInputStream(new File(path + "/"
                    + fileName));
            System.out.println(path + fileName);

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 关闭输出流
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
