package com.sast.woc.config;

import com.sast.woc.intercept.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
        @Autowired
        private  TokenInterceptor tokenInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        }



}
