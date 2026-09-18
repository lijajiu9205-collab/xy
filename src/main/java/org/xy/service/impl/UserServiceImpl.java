package org.xy.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.xy.common.Result;
import org.xy.entity.User;
import org.xy.mapper.UserMapper;
import org.xy.service.UserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Result<Void> register(User user) {
//        if(StrUtil.isBlank(user.getPhone()))
        return null;
    }
    @Override
    public Result<String> login(User user) {
        return null;
    }
}
