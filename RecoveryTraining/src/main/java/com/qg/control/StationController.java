package com.qg.control;

import com.qg.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 车站类
 * Created by symon on 16-12-23.
 */
@Controller
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping("/getStationById")
    @ResponseBody
    public Object getStationById(@RequestParam("stationId") Integer stationId) {
        return stationService.getStationById(stationId);
    }
}
