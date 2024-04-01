package com.tutorial.config.autoconfig;

import com.tutorial.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MyAutoConfiguration
public class TomcatWebServerConfiguration {
    @Bean
    public ServletWebServerFactory servletWebServletFactory() {
        return new TomcatServletWebServerFactory();
    }
}
