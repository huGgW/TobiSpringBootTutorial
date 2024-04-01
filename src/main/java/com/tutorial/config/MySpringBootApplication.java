package com.tutorial.config;

import com.tutorial.config.EnableAutoConfiguration;
import com.tutorial.config.autoconfig.DispatcherServletConfiguration;
import com.tutorial.config.autoconfig.TomcatWebServerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Configuration, ComponentScan annotation을 둘 다 포함한 Composed Annotation 생성
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// Configuration: Component를 포함한 메타 어노테이션
@Configuration
@ComponentScan
@EnableAutoConfiguration
public @interface MySpringBootApplication {
}
