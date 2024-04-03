package com.tutorial.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component // Component는 ComponentScan에 의한 방법과 Import에 의한 방법이 있음
public @interface MyConfigurationProperties {
    String prefix();
}
