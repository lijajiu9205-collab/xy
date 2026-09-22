package org.xy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId
    //主键
    private Long id;
    //学号
    private String username;
    //密码
    private String password;
    //用户名
    private String nickname;
    //手机号
    private String phone;
    //头像
    private String avatar;
    //学院/校区
    private String school;
    //身份 0学生 1管理员
    private Integer role;
    //状态 1正常 0禁用
    private Integer status;
    //注册时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;

}
