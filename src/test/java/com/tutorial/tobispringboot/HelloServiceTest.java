package com.tutorial.tobispringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface FastUnitTest { }

// Test는 메타 어노테이션이라 적용 가능
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE}) // Annotation Type을 추가하여 meta annotation으로 활용 가능하도록 설정
@Test
@interface UnitTest { }


public class HelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        var helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @FastUnitTest
    void helloDecorator() {
        var decorator = new HelloDecorator(name -> name);

        var ret = decorator.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }
}
