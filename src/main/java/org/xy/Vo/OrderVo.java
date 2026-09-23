package org.xy.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//我的订单出参vo
@Data
public class OrderVo {

    // ===== 第一部分：从 Orders 实体照搬，页面要显示的 =====
    private Long id;
    private String orderNo;           // 订单号，展示给用户看
    private Long productId;           // 点订单能跳回商品详情
    private String titleSnapshot;     // 下单时的标题快照（卖家后来改标题也不影响历史订单）
    private BigDecimal priceSnapshot; // 下单时的成交价快照（价格在这一刻就冻结了）
    private String tradePlace;        // 约定的交易地点
    private Integer status;           // 0待付款 1已付款 2已完成 3已取消
    private LocalDateTime createTime; // 下单时间

    // ===== 第二部分：join 补出来的，Orders 实体上没有 =====
    private String coverImg;          // 拿 productId 去 product 表查（本页查一次，别每行查一次）
}
