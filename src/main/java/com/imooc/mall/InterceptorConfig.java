package com.imooc.mall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())//拦截住了，怎么处理
                .addPathPatterns("/**")//要拦截哪些
                .excludePathPatterns("/error","/user/login","/user/register","/categories","/products","/products/*");
    }
}
