package org.xy.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.xy.common.Result;
import org.xy.entity.User;

public interface UserService extends IService<User> {
    Result<String> login(User user);
    Result<Void> register(User user);
}
