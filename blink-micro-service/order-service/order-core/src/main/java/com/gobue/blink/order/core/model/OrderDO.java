package com.gobue.blink.order.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDO {
//implements DTOConvert<AccountDTO>

    private Long id;
    private String orderId;
    private String sku;
    private String price;
    private Integer quantity;

    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

//    @Override
//    public AccountDTO convert() {
//        return BeanUtils.transform(AccountDTO.class, this);
//    }

}
