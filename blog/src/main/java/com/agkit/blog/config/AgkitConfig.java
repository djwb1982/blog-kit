package com.agkit.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: com.agkit.uploader.config.UploaderConfig
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-05-21 23:01
 **/
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "agkit.config")
public class AgkitConfig {
    @Value("fileRoot")
    private  String fileRoot;
    @Value("pathFix")
    private  String pathFix;
    @Value("webPath")
    private  String webPath;
}
