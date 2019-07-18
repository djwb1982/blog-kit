package com.agkit.blog.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ThymeleafConfig
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-07-17 18:41
 **/
@Configuration
public class ThymeleafConfig implements ApplicationContextAware {
    private static final String UTF8 = "UTF-8";
    private ApplicationContext applicationContext;
	@Resource
	private AgkitConfig agkitConfig;
    private String[] array(String... args) {
        return args;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        if (viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("webPath", agkitConfig.getWebPath());
            viewResolver.setStaticVariables(vars);
        }
    }
}
