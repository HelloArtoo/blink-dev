package com.gobue.blink.order.core.web.impl;

import com.gobue.blink.order.api.constant.Result;
import com.gobue.blink.order.api.dto.OrderDTO;
import com.gobue.blink.order.core.model.OrderDO;
import com.gobue.blink.order.core.service.OrderService;
import com.gobue.blink.order.core.web.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderResourceImpl implements OrderResource {

    @Autowired
    OrderService orderService;

    @Override
    public Result<OrderDTO> getByOrderId(String orderId) {

        try {
            OrderDO byOrderId = orderService.getByOrderId(orderId);

        } catch (Exception e) {
            //LOGO
        }

        return new Result<OrderDTO>();
    }
}
