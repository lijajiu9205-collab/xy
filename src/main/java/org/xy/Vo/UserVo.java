package org.xy.Vo;

import lombok.Data;

//我的信息出参vo
@Data
public class UserVo {

    // 注意：这个类【故意】不写 password 字段
    // User 里存的是 BCrypt 哈希，虽然不能反推明文，但漏出去能被离线爆破
    // 这里没有这个字段 → 就算用 BeanUtil.copyProperties(user, vo) 一把拷，密码也进不来
    private Long id;
    private String username; // 学号
    private String nickname; // 昵称
    private String phone;    // 手机号
    private String avatar;   // 头像
    private String school;   // 学院/校区
}
