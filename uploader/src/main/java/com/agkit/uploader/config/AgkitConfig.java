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
@Component
@Configuration
@ConfigurationProperties(prefix = "agkit.config")
public class AgkitConfig {

    private static  String fileRoot;
    private static  String pathFix;
    private static  String webPath;
    private static String allowUrl;

    public static String getFileRoot() {
        return fileRoot;
    }

    @Value("fileRoot")
    public void setFileRoot(String fileRoot) {
        AgkitConfig.fileRoot = fileRoot;
    }

    public static String getPathFix() {
        return pathFix;
    }

    @Value("pathFix")
    public void setPathFix(String pathFix) {
        AgkitConfig.pathFix = pathFix;
    }

    public static String getWebPath() {
        return webPath;
    }

    @Value("webPath")
    public void setWebPath(String webPath) {
        AgkitConfig.webPath = webPath;
    }

    public static String getAllowUrl() {
        return allowUrl;
    }

    @Value("allowUrl")
    public void setAllowUrl(String allowUrl) {
        AgkitConfig.allowUrl = allowUrl;
    }
/*   private static String staticFileRoot;
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
*/
}
