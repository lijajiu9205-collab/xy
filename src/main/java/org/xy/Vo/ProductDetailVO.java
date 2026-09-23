package org.xy.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//详情页的加工出参vo
@Data
public class ProductDetailVO {

    // ===== 第一部分：从 Product 实体照搬过来的，页面要显示的 =====
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer conditionLv;   // 成色 1全新 2九成新 3一般
    private String coverImg;
    private String tradePlace;
    private Integer status;
    private Integer viewCount;     // 文档出参表漏了，但详情页要显示"浏览量"，所以补上
    private Long categoryId;
    private Long sellerId;
    private LocalDateTime createTime;

    // ===== 第二部分：join 补出来的，Product 实体上没有 =====
    private String categoryName;    // 拿 categoryId 去 category 表查
    private String sellerNickname;  // 拿 sellerId 去 user 表查
    private List<String> images;    // 拿 id 去 product_image 表查，按 sort 升序
}
