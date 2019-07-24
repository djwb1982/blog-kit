package com.agkit.uploader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: com.agkit.uploader.config.ResourceConfigAdapter
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-07-23 22:38
 **/

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String path = AgkitConfig.getFileRoot() + AgkitConfig.getPathFix();
        registry.addResourceHandler("/"+AgkitConfig.getPathFix() + "/**").addResourceLocations("file:" + path);
    }
}
