package com.tutorial.config;

import com.tutorial.config.autoconfig.DispatcherServletConfiguration;
import com.tutorial.config.autoconfig.TomcatWebServerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// Import: 주어진 class를 구성 정보에 추가 (scan 범위가 아니더라도 추가됨)
//@Import({
//        TomcatWebServerConfiguration.class,
//        DispatcherServletConfiguration.class,
//})
// Import Selector를 구현한 class를 Import하는 경우,
// selectImports에서 반환된 string 값의 class paths들을 import
@Import(MyAutoConfigImportSelector.class)
public @interface EnableAutoConfiguration {}
