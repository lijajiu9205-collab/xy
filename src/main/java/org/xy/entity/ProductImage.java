package org.xy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("product_image")
public class ProductImage {
    @TableId
    //主键
    private Long id;
    //所属商品
    private Long productId;
    //图片路径
    private String imgUrl;
    //展示顺序
    private Integer sort;
    //上传时间
    private LocalDateTime createTime;
}
