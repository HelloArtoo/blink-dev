package com.gobue.blink.order.api.service;


import com.gobue.blink.order.api.dto.OrderDTO;

public interface OrderRpcService {

    /**
     * 通过订单ID查询订单明细
     *
     * @param orderId 订单ID
     * @return
     */
    OrderDTO getOrderByOrderId(String orderId);

}
