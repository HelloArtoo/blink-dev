package com.gobue.blink.order.core.web;

import com.gobue.blink.order.api.constant.Result;
import com.gobue.blink.order.api.dto.OrderDTO;

public interface OrderResource {

    /**
     * 通过订单ID查询订单明细
     *
     * @param orderId 订单ID
     * @return
     */
    Result<OrderDTO> getByOrderId(String orderId);

}
