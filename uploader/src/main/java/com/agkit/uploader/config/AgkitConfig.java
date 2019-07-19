package com.agkit.uploader.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
    @Value("fileRoot")
    private  String pathFix;
    @Value("webPath")
    private  String webPath;
    @Value("allowUrl")
    private String allowUrl;

    private static String staticFileRoot;
    private static String staticPathFix;
    private static String staticWebPath;

    private static String staticAllowUrl;

    @PostConstruct
    public void init(){
        staticFileRoot=this.fileRoot;
        staticPathFix=this.pathFix;
        staticWebPath=this.webPath;
        staticAllowUrl=this.allowUrl;
    }

    public static String getStaticFileRoot(){
        return staticFileRoot;
    }

    public static String getStaticPathFix(){
        return staticPathFix;
    }

    public static String getStaticWebPath(){
        return staticWebPath;
    }

    public static String getStaticAllowUrl() {
        return staticAllowUrl;
    }

}
