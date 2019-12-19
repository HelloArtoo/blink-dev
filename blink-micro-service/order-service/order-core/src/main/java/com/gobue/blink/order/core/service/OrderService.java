package com.gobue.blink.order.core.service;

import com.gobue.blink.order.core.model.OrderDO;

public interface OrderService {

    /**
     * 通过订单ID查询订单明细
     *
     * @param orderId 订单ID
     * @return
     */
    OrderDO getByOrderId(String orderId);

}
