package com.qg.control;

import com.mchange.v1.identicator.IdHashMap;
import com.qg.control.base.BaseController;
import com.qg.entity.Relation;
import com.qg.entity.User;
import com.qg.entity.UserRelationKey;
import com.qg.service.RelationService;
import com.qg.service.UserRelationService;
import com.qg.util.GsonUtil;
import com.qg.util.NotEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wf on 2016/12/23.
 */
@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private UserRelationService userRelationService;

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(HttpSession session, Relation relation) {
        Map<String, Object> result = new HashMap();
        if(!NotEmptyUtil.isNotNull(relation.getCertNo(), relation.getName())) {
            return BaseController.UN_SUCCESS;
        }
        User user = (User)session.getAttribute("user");
        if(user == null) {
            result.put("result", 0);
            result.put("info", "请先登陆");
        } else {
            UserRelationKey key = new UserRelationKey();
            key.setUserId(user.getId());
            key.setRelationId(relation.getCertNo());
            int flag = relationService.insert(relation);
            int flag1 = userRelationService.insert(key);
            int flag2 = flag + flag1;
            result.put("result", flag2);
        }
        return GsonUtil.gson.toJson(result);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") String id) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", relationService.delete(id));
        return GsonUtil.gson.toJson(result);
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public String selectAll(HttpSession session) {
        Map<String,Object> result = new HashMap<>();
        User user = (User)session.getAttribute("user");
        List<Relation> list = new ArrayList<>();
        if(user == null) {
            result.put("result", false);
            result.put("info", "请先登陆");
        } else {
            list = relationService.selectByUser(user.getId());
            result.put("result", true);
            result.put("data", list);
        }
        return GsonUtil.gson.toJson(result);
    }

}
