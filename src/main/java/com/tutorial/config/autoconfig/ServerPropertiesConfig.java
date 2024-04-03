package com.tutorial.config.autoconfig;

import com.tutorial.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

// Property를 담은 class를 Bean으로 설정.
// Property가 늘어날 수록 여러 class를 생성해야 하고
// Conditional의 조건이 매우 복잡한 단점 존재
//@MyAutoConfiguration
//public class ServerPropertiesConfig {
//    @Bean
//    public ServerProperties serverProperties(Environment environment) {
//        // Binder가 주어진 type의 setter들에게
//        // 알맞은 environment value를 binding 해주고
//        // get을 통해 해당 class 객체를 반환해줌
//        return Binder.get(environment).bind("", ServerProperties.class).get();
//    }
//}
