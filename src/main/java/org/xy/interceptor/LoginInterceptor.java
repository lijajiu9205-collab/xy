package org.xy.interceptor;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xy.common.UserContext;
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;
    //重写preHandle，在执行controller之前的拦截，内含http请求request，http响应response，object类型handler
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.拿token
        //前端请求：Authorization:Bearer token
        String token=request.getHeader("Authorization");
        //2.校验token
        if(StrUtil.isBlank(token)||!token.startsWith("Bearer ")) {
            response.setStatus(401);
            return false;}
        //此时查到的token为Bearer token,把前七位去掉
        token=token.substring(7);
        //3.查redis
        String userId=stringRedisTemplate.opsForValue().get("login:token:"+token);
        //4.判断结果
        if(userId==null){
            response.setStatus(401);
            return false;}
        //5.存进usercontext，string类型要转Long
        UserContext.setUserId(Long.valueOf(userId));
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContext.remove();
    }
}
