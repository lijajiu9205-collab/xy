package org.xy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {
    @TableId
    //主键
    private Long id;
    //商品标题
    private String title;
    //详细描述
    private String description;
    //售价，保留两位小数
    private BigDecimal price;
    //成色 1全新 2九成新 3七成新 4一般
    private Integer conditionId;
    //所属分类id
    private Integer categoryId;
    //发布者id
    private Integer sellerId;
    //封面图
    private String coverImg;
    //期望交易地点
    private String tradePlace;
    //审核状态 0待审核 1在售 2已锁定 3已售出 4已下架
    private Integer status;
    //逻辑删除 0否1是
    private Integer isDeleted;
    //浏览次数
    private Integer viewCount;
    //发布时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
