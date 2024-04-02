package com.tutorial.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

// ImportSelector가 아닌 DeferredImportSelector를 구현한 이유:
// User가 등록한 구성 정보를 모두 load한 후에 auto configuration에 의한 구성 정보를 load하기 위해
public class MyAutoConfigImportSelector
        implements DeferredImportSelector
{
    // ClassLoader: class 파일을 읽어들이는 역할
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[]{
//                "com.tutorial.config.autoconfig.TomcatWebServerConfiguration",
//                "com.tutorial.config.autoconfig.DispatcherServletConfiguration"
//        };


        List<String> autoConfigs = new ArrayList<>();

        // ImportCandidates의 load method는 META-INF/spring/full-path-to-annotation-class.imports
        // 내부의 String 값들을 line별로 읽어와서 String Iterator로 반환
        ImportCandidates
                .load(MyAutoConfiguration.class, classLoader)
                .forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);

//        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
//        return StreamSupport
//                .stream(candidates.spliterator(), false)
//                .toArray(String[]::new);
    }
}
