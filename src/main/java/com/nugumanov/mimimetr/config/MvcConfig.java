package com.nugumanov.mimimetr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Aizat Nugumanov
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Value("${image.upload.path}")
    private String imageUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("file:///" + imageUploadPath + "/", "classpath:/static/img/cats/");
        registry
                .addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
    }
}
