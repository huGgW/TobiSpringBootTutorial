package com.tutorial.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        var attr = importingClassMetadata.getAllAnnotationAttributes(
                EnableMyConfigurationProperties.class.getName()
        );
        Class propertyClass = (Class) attr.getFirst("value");
        return new String[]{ propertyClass.getName() };
    }
}
