package com.qg.control;

import com.qg.control.base.BaseController;
import com.qg.entity.TrainFrequency;
import com.qg.service.TrainFrequencyService;
import com.qg.util.GsonUtil;
import com.qg.util.NotEmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班次类
 * Created by wf on 2016/12/23.
 */
@Controller
@RequestMapping("/trainFrequency")
public class TrainFrequencyController {

    @Autowired
    private TrainFrequencyService trainFrequencyService;

    @RequestMapping("/insertTrainFrequency")
    @ResponseBody
    public String insertTrainFrequency(TrainFrequency trainFrequency) {
        Map<String, Object> result = new HashMap<>();
        int flag = 0;
        if(!NotEmptyUtil.isNotNull(trainFrequency.getBeginTime(), trainFrequency.getEndTime(),
                trainFrequency.getBeginStationId(), trainFrequency.getEndStationId()
                ,trainFrequency.getTrainId())) {
            return BaseController.UN_SUCCESS;
        }
        try {
            flag = trainFrequencyService.insert(trainFrequency);
        }   catch(Exception e) {
            e.printStackTrace();
        }
        result.put("result", flag);
        return GsonUtil.gson.toJson(result);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll() {
        List<TrainFrequency> list = new ArrayList<>();
        list = trainFrequencyService.selectAll();
        return GsonUtil.gson.toJson(list);
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public Object selectById(@RequestParam("id") Integer id) {
        return trainFrequencyService.selectById(id);
    }

    @RequestMapping("/selectByEndStationId")
    @ResponseBody
    public String selectByEndStationId(@RequestParam("endStationId") Integer id) {
        List<TrainFrequency> list = new ArrayList<>();
        list = trainFrequencyService.selectByEndStation(id);
        return GsonUtil.gson.toJson(list);
    }


}
