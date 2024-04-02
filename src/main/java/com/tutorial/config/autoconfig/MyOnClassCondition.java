package com.tutorial.config.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

public class MyOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 주어진 값 (class path string value)를 찾는다.
        var attrs = metadata.getAnnotationAttributes(
                ConditionalMyOnClass.class.getName()
        );
        var value = (String) attrs.get("value");

        // context에 해당 class가 존재한다면 true를 반환하여 구성 정보에 포함하도록 한다.
        return ClassUtils.isPresent(
                value,
                context.getClassLoader()
        );
    }
}
