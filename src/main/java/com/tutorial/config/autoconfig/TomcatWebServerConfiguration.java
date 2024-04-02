package com.tutorial.config.autoconfig;

import com.tutorial.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@MyAutoConfiguration
//@Conditional(TomcatWebServerConfiguration.TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfiguration {
    @Bean("tomcatWebServerFactory")
    // Conditional은 method에도 적용 가능
    @ConditionalOnMissingBean // 사용자가 등록한 bean이 존재하지 않는 경우에만 자동으로 넣어준다.
    public ServletWebServerFactory servletWebServletFactory() {
        return new TomcatServletWebServerFactory();
    }

//    static class TomcatCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent(
//                    "org.apache.catalina.startup.Tomcat",
//                    context.getClassLoader()
//            );
//        }
//    }
}
