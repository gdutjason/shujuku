package com.qg.control;

import com.qg.control.base.BaseController;
import com.qg.entity.TrainFrequency;
import com.qg.entity.TrainOrder;
import com.qg.entity.User;
import com.qg.entity.vo.OrderVo;
import com.qg.service.TrainFrequencyService;
import com.qg.service.TrainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static javafx.scene.input.KeyCode.T;
import static javax.swing.text.html.CSS.getAttribute;

/**
 * 售票功能实现
 * Created by symon on 16-12-23.
 */
@RestController
@RequestMapping("/order")
public class TrainOrderController {

    @Autowired
    private TrainOrderService orderService;

    @Autowired
    private TrainFrequencyService trainFrequencyService;

    /**
     * 售票
     */
    @RequestMapping("/sell")
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

    /**
     * 查看该用户的所有订单
     */
    @RequestMapping("/getAllOrder")
    public Object getAllOrder(HttpSession session) {
        if(session.getAttribute("user") == null) {
            return BaseController.UN_LOGIN;
        }

        User user = (User) session.getAttribute("user");
        List<TrainOrder> orderList = orderService.getAllUserOrder(user);
        if(CollectionUtils.isEmpty(orderList)) {
            return Collections.EMPTY_LIST;
        }
        List<OrderVo> resultList = new ArrayList<>(orderList.size());
        for(TrainOrder order : orderList) {
            OrderVo orderVo = new OrderVo();
            orderVo.setTrainFrequency(trainFrequencyService.getEntity(order.getTrainFrequencyId()));
            orderVo.setId(order.getId());
            orderVo.setCreateTime(order.getCreateTime());
            orderVo.setOrderRelation(orderService.getRelationInOrder(order.getId()));
            orderVo.setTrainName(trainFrequencyService.getTrain(order.getTrainFrequencyId()).getName());
            resultList.add(orderVo);
        }

        return resultList;
    }

    /**
     * 退票
     */
    @RequestMapping("/refund")
    public String refund(HttpSession session, Integer orderId) {
        if(session.getAttribute("user") == null) {
            return BaseController.UN_LOGIN;
        }
        orderService.refund(orderId);
        return BaseController.SUCCESS;
    }
}
