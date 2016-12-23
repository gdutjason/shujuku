package com.qg.control;

import com.mchange.v1.identicator.IdHashMap;
import com.qg.control.base.BaseController;
import com.qg.entity.Relation;
import com.qg.service.RelationService;
import com.qg.util.GsonUtil;
import com.qg.util.NotEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wf on 2016/12/23.
 */
@Controller
@RequestMapping("/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Relation relation) {
        if(!NotEmptyUtil.isNotNull(relation.getCertNo(), relation.getName())) {
            return BaseController.UN_SUCCESS;
        }
        int flag = relationService.insert(relation);
        Map<String, Object> result = new HashMap();
        result.put("result", flag);
        return GsonUtil.gson.toJson(result);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") String id) {
        return GsonUtil.gson.toJson(relationService.delete(id));
    }



}
