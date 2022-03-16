package com.andy.seata.config;

import com.taobao.txc.client.springcloud.TxcInboundHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Andy
 * @Date: 2022/03/15/ 13:05
 * @Description
 */
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TxcInboundHandler()).addPathPatterns(new String[] { "/**" });

    }
}
