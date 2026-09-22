package org.xy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xy.interceptor.LoginInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate))
                // 先全拦：/api 下任意层级都要过登录校验
                .addPathPatterns("/api/**")
                // 再挖洞：这些不用登录
                .excludePathPatterns(
                        "/api/login",           // 登录（密码/验证码同一入口）
                        "/api/login/phone",     // 验证码登录（文档保留的旧入口）
                        "/api/register",        // 注册
                        "/api/code",            // 发验证码
                        "/api/category",        // 分类列表（游客可看）
                        "/api/product",         // 商品列表 / 搜索（游客可看）
                        "/api/product/**");     // 商品详情（游客可看）
    }
}
