package org.xy.service.impl;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.xy.Bo.LoginBo;
import org.xy.common.Result;
import org.xy.entity.User;
import org.xy.mapper.UserMapper;
import org.xy.service.UserService;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final StringRedisTemplate stringRedisTemplate;
    public Result<String> register(LoginBo loginBo) {
        User user=new User();
        //1.验证账号密码是否为空
        if(StrUtil.isBlank(loginBo.getPhone())||StrUtil.isBlank(loginBo.getPassword()))
            return Result.error(400,"账号或密码不能为空");
        if(!loginBo.getPhone().matches("^1[3-9]\\d{9}$"))
            return Result.error(400,"手机号格式错误");
        //2.手机号跟数据库匹配查重
        Long count=lambdaQuery().eq(User::getPhone,loginBo.getPhone()).count();
        if(count>0)
            return Result.error(400,"手机号已被注册");
        //这里通过要有传递验证码的功能了
        if(StrUtil.isBlank(loginBo.getCode()))
            return Result.error(400,"验证码为空");
        String key="login:code:"+loginBo.getPhone();
        String cacheCode=stringRedisTemplate.opsForValue().get(key);
        if(cacheCode==null)
            return Result.error(400,"验证码已过期");
        if(!cacheCode.equals(loginBo.getCode()))
            return Result.error(400,"验证码错误");
        //校验成功，发token直接登录
        user.setPhone(loginBo.getPhone());// ← 搬 phone
        //3.密码用BCrypt方式加密
        user.setPassword(BCrypt.hashpw(loginBo.getPassword(), BCrypt.gensalt()));  // ← 搬 + 加密
        //4.先用默认账号当做昵称
        user.setNickname( "用户" + StrUtil.subSuf(loginBo.getPhone(),4));// 没填 → 用户8000
        save(user);
        stringRedisTemplate.delete(key);
        String token=IdUtil.fastSimpleUUID();
        stringRedisTemplate.opsForValue().set("login:token:"+token,user.getId().toString(),7,TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result<String> sendCode(String phone) {
        //防刷：设置key防止redis被恶意攻击造成崩溃
        String key="login:code:"+phone;
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key)))
            return Result.error(400,"请求太频繁，请稍后再试");
        //2.符合，生成验证码
        String code= RandomUtil.randomNumbers(4);
        //3.保存验证码到redis
        stringRedisTemplate.opsForValue().set(key,code,1, TimeUnit.MINUTES);
        //4.发送验证码给前端
        return Result.success(code);
    }


    //登录有两种方式1.手机号+验证码 2.手机号+密码
    @Override
    public Result<String> loginByPassword(LoginBo loginBo) {
        //1.手机号+密码
        if(StrUtil.isBlank(loginBo.getPhone())||StrUtil.isBlank(loginBo.getPassword()))
            return Result.error(400,"账号或密码不得为空");
        if(StrUtil.isBlank(loginBo.getPhone())||!loginBo.getPhone().matches("^1[3-9]\\d{9}$"))
            return Result.error(400,"手机号格式错误");
        User user=lambdaQuery().eq(User::getPhone,loginBo.getPhone()).one();
        if(user==null)
            return Result.error(400,"用户未注册");
        if(!BCrypt.checkpw(loginBo.getPassword(),user.getPassword()))
            return Result.error(400,"密码错误");
        String token= IdUtil.fastSimpleUUID();
        stringRedisTemplate.opsForValue().set("login:token:"+token,user.getId().toString(),7,TimeUnit.DAYS);
        return Result.success(token);
    }
    @Override
    public Result<String> loginByPhone(LoginBo loginBo) {
        //手机号+验证码
        //1.校验手机号
        if(StrUtil.isBlank(loginBo.getPhone()))
            return Result.error(400,"手机号不能为空");
        if(StrUtil.isBlank(loginBo.getPhone())||!loginBo.getPhone().matches("^1[3-9]\\d{9}$"))
            return Result.error(400,"手机号格式错误");
        User user=lambdaQuery().eq(User::getPhone,loginBo.getPhone()).one();
        if(user==null)
            return Result.error(400,"用户未注册");
        //接受到验证码后继续校验
        if(StrUtil.isBlank(loginBo.getCode()))
            return Result.error(400,"验证码为空");
        String key="login:code:"+loginBo.getPhone();
        String cacheCode=stringRedisTemplate.opsForValue().get(key);
        if(cacheCode==null)
            return Result.error(400,"验证码已过期");
        if(!cacheCode.equals(loginBo.getCode()))
            return Result.error(400,"验证码错误");
        stringRedisTemplate.delete(key);
        //校验成功，发送token
        String token =IdUtil.fastSimpleUUID();
        stringRedisTemplate.opsForValue().set("login:token:"+token,user.getId().toString(),7,TimeUnit.DAYS);
        return Result.success(token);
    }

}
