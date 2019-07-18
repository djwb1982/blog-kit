package com.agkit.uploader;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: com.agkit.uploader.SpringBootStartApplication
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-05-04 07:25
 **/
public class SpringBootStartApplication extends SpringBootServletInitializer {
      @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(UploaderApplication.class);
    }
}
