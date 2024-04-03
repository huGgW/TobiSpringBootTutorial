package com.tutorial.config.autoconfig;

import com.tutorial.config.MyAutoConfiguration;
import com.tutorial.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment environment) {
        return new BeanPostProcessor() {
            // Bean object의 초기화가 끝난 후 아래 작업을 실행
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                // 해당 bean의 MyConfigurationProperties annotation이 추가된 경우 이를 반환.
                var annotation = AnnotationUtils.findAnnotation(
                        bean.getClass(),
                        MyConfigurationProperties.class
                );

                // MyConfigurationProperties annotation이 추가된 경우에만 작업
                if (annotation == null) {
                    return bean;
                }

                var attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                var prefix = (String) attrs.get("prefix");

                // Binder를 통해 해당 bean에 environment 값을 set해줌.
                // name은 prefix를 의미함.
                return Binder.get(environment)
                        .bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
