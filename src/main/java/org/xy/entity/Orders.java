package org.xy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("orders")
public class Orders {
    @TableId
    //主键
    private Long id;
    //业务订单号，展示给用户
    private String orderNo;
    //商品id
    private Long productId;
    //买家id
    private Long buyerId;
    //卖家id
    private Long sellerId;
    //下单时的商品标题快照
    private String titleSnapshot;
    //下单时的成交价快照
    private BigDecimal priceSnapshot;
    //约定的交易地点
    private String tradePlace;
    //订单状态 0待付款 1已付款 2已完成 3已取消
    private Integer status;
    //取消原因
    private String cancelReason;
    //标记付款时间
    private LocalDateTime payTime;
    //完成时间
    private LocalDateTime finishTime;
    //取消时间
    private LocalDateTime cancelTime;
    //下单时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
