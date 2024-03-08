package com.tutorial.tobispringboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
@Component annotation은 Spring Container에 bean으로 등록하기 위한 annotation이다.
이는 meta annotation이므로, type 뿐만 아니라 annotation 위에도 붙일 수 있다.
대표적인 예시로는 streotype annotation이 있다. (ex. @Controller, @Service, @Repository)
 */
@Retention(RetentionPolicy.RUNTIME) // 언제까지 살아있을 것인가
@Target({ElementType.TYPE}) // 어디에 붙일 수 있는가
@Component
public @interface MyComponent {
}
