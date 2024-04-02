package com.tutorial.config.autoconfig;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import com.tutorial.config.MyAutoConfiguration;

@MyAutoConfiguration
// Conditional annotation을 추가함으로써 어떤 조건에서만 해당 class가 구성으로써 추가되도록 할 수 있다.
// Condition을 implement한 class를 인자로 반드시 주어야 함.
// 주어진 class를 등록할 건지 구현한 matches method에서 반환.
//@Conditional(UndertowWebServerConfiguration.UntertowCondition.class)
@ConditionalMyOnClass("io.undertow.Undertow")
public class UndertowWebServerConfiguration {
    @Bean("undertowWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

//    static class UntertowCondition implements Condition {
//        // ConditionContext: 현재 spring이 동작하고 있는 context에 대한 정보를 가지고 있는 object
//        // AnnotatedTypeMetadata: 해당 annotation이 붙은 class의 다른 annotation들에 대한 정보를 가지고 있는 object
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent(
//                    "io.undertow.Undertow",
//                    context.getClassLoader()
//            );
//        }
//    }
}
