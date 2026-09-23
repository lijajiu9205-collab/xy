package org.xy.Bo;

import lombok.Data;
//首页接口功能bo
@Data
public class ProductQueryBo {
    private Integer pageNum= 1;
    private Integer pageSize=10;
    private Long categoryId;
    private String keyword;
}
