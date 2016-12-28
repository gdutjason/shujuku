package com.qg.control;

import com.qg.control.base.BaseController;
import com.qg.entity.Station;
import com.qg.entity.Train;
import com.qg.entity.TrainFrequency;
import com.qg.entity.vo.TrainFrequencyVo;
import com.qg.service.StationService;
import com.qg.service.TrainFrequencyService;
import com.qg.service.TrainService;
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
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;

    @RequestMapping("/insertTrainFrequency")
    @ResponseBody
    public String insertTrainFrequency(TrainFrequencyVo tfVo) {
        Map<String, Object> result = new HashMap<>();
        if(!NotEmptyUtil.isNotNull(tfVo.getBeginTime(), tfVo.getEndTime(),
                tfVo.getBeginStationName(), tfVo.getEndStationName()
                ,tfVo.getTrainName(), tfVo.getMaxNum())) {
            return BaseController.UN_SUCCESS;
        }
        try {
            result = trainFrequencyService.insert(tfVo);
        }   catch(Exception e) {
            e.printStackTrace();
        }
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

    @RequestMapping("/selectByEndStationName")
    @ResponseBody
    public String selectByEndStationId(@RequestParam("endStationName") String name) {
        List<TrainFrequency> list = new ArrayList<>();
        list = trainFrequencyService.selectByEndStation(name);
        return GsonUtil.gson.toJson(list);
    }

}
