package org.xy.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.xy.Bo.LoginBo;
import org.xy.common.Result;
import org.xy.entity.User;

public interface UserService extends IService<User> {
    Result<String> loginByPassword(LoginBo loginBo);
    Result<String> register(LoginBo loginBo);
    
    Result<String> sendCode(String phone);

    Result<String> loginByPhone(LoginBo loginBo);
}
