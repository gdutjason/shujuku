package com.qg.control;

import com.qg.control.base.BaseController;
import com.qg.entity.User;
import com.qg.service.TrainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 售票功能实现
 * Created by symon on 16-12-23.
 */
@RestController
@RequestMapping("/order")
public class TrainOrderController {

    @Autowired
    private TrainOrderService orderService;

    /**
     * 售票
     */
    @RequestMapping("/")
    public Object sell(HttpSession httpSession, String[] relationIdGroup, Integer frequencyId) {
        if(httpSession.getAttribute("user") == null) {
            return BaseController.UN_LOGIN;
        }
        if(relationIdGroup.length == 0) {
            return BaseController.SUCCESS;
        }
        return orderService.sell(Arrays.asList(relationIdGroup), frequencyId,
                ((User)httpSession.getAttribute("user")).getId());
    }
}
