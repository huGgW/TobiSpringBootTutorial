package com.tutorial.config.autoconfig;

import com.tutorial.config.EnableMyConfigurationProperties;
import com.tutorial.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
// import를 통해 주입하는 경우 해당 configuration이 필요해 load되는 경우만 Import의 bean도 load됨.
// 복잡한 설정 없이 conditional import 가능.
//@Import(ServerProperties.class)
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfiguration {
//    // property에서 주입받아 field를 설정
//    // 흔히 이렇게 사용하지만 치환자를 교체해주는 것이 기본 동작 방식은 아니다.
//    // Spring Container에 기능을 추가해주어야 한다.
//    // 이것은 PropertySourcesPlaceholderConfigurer bean이 담당한다.
//    @Value("${contextPath}")
//    String contextPath;
//
//    // integer 등 spring container가 변환해 줄 수 있는 타입은 알아서 변환해줌
//    // ':'을 붙임으로써 값이 존재하지 않는 경우 뒤의 값을 default value로 사용하도록 설정
//    @Value("${port:8080}")
//    int port;

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    // Environment bean을 주입받음
    public ServletWebServerFactory servletWebServletFactory(
            /*Environment env*/
            ServerProperties properties
    ) {
        var factory = new TomcatServletWebServerFactory();

        // property로부터 값 가져옴.
//        factory.setContextPath(env.getProperty("contextPath")); // 모든 api의 root path

//        // 주입받은 field 값을 사용
//        factory.setContextPath(contextPath);
//        factory.setPort(port);


        factory.setContextPath(properties.getContextPath());
        factory.setPort(properties.getPort());

        return factory;
    }
}
