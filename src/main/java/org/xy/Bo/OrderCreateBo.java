package org.xy.Bo;

import lombok.Data;
//下单功能的入参bo
@Data
public class OrderCreateBo {
    private Long productId;
    private String tradePlace;
}
