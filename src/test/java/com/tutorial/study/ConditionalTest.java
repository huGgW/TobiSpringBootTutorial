package com.tutorial.study;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {
    @Test
    void conditional() {
        // ApplicationContextRunner, hasSinlgeBean을 이용하여
        // 원하는 configuration을 넣은 context를 생성하여 bean을 테스트해볼 수 있음.
        // ApplicationContextRunner는 Spring Boot에서 제공하는 테스트용 유틸리티 클래스.
        // AssertJ에서 bean을 체크해보는 함수도 추가적으로 제공

        // true
        var contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class)
            .run(context -> {
                Assertions.assertThat(context).hasSingleBean(MyBean.class);
                Assertions.assertThat(context).hasSingleBean(Config1.class);
            });


        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
            .run(context -> {
                Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
                Assertions.assertThat(context).doesNotHaveBean(Config2.class);
            });
    }

    @Configuration
    @BooleanConditional(true)
//    @TrueConditional
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
//    @FalseConditional
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    // Conditional은 메타 에노테이션이므로
    // 특정 condition을 포함하는 Annotation 자체를 만들 수 있음.
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }


    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // Annotation에서 attribute 정보를 가져오기 위해 AnnotatedTypeMetadata 사용
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                    BooleanConditional.class.getName()
            );
            Boolean value = (Boolean)annotationAttributes.get("value");
            return value;
        }
    }

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

}

// 참고: 중첩된 static class란?
//// 외부 class와는 아무 상관이 없는 class를 내부에 정의할 때 사용
//// 외부 class는 패키지와 비슷한 역할을 할 뿐