package com.gobue.blink.order.api.dto;

import java.math.BigDecimal;

/**
 * 订单
 */
@Data
public class OrderDTO {

    private String orderId;
    private String sku;
    private BigDecimal price;



}
