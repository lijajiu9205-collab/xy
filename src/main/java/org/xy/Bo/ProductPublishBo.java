package org.xy.Bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

//发布订单接口功能的建bo
@Data
public class ProductPublishBo {
    private String title;
    private String description;
    private BigDecimal price;
    private Integer conditionLv;
    private Long categoryId;
    private String coverImg;
    private String tradePlace;
    private List<String> images;
}
