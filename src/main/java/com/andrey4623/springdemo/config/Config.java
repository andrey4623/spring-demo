package com.andrey4623.springdemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
public class Config {

  @Bean
  public FreeMarkerViewResolver freeMarkerViewResolver() {
    FreeMarkerViewResolver r = new FreeMarkerViewResolver();
    r.setCache(true);
    r.setPrefix("");
    r.setSuffix(".ftl");
    return r;
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer c = new FreeMarkerConfigurer();
    c.setTemplateLoaderPath("/WEB-INF/freemarker/");

    return c;
  }

}
