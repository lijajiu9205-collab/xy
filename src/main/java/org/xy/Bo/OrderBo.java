package org.xy.Bo;

import lombok.Data;

//我的订单的入参bo
@Data
public class OrderBo {
    private String type;
    private Integer pageNum=1;
    private Integer pageSize=10;
}
