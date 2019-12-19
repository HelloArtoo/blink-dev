package com.gobue.blink.order.core.service.impl;

import com.gobue.blink.order.core.mapper.OrderMapper;
import com.gobue.blink.order.core.model.OrderDO;
import com.gobue.blink.order.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    public OrderDO getByOrderId(String orderId) {
        return orderMapper.getByOrderId(orderId);
    }
}
