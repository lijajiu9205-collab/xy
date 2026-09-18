package org.xy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("category")
public class Category {
    @TableId
    //主键
    private Long id;
    //分类名
    private String name;
    //排序号
    private Integer sort;
    //状态 1启用 0停用
    private Integer status;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
}
