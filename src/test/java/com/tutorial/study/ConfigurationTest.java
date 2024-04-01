package com.tutorial.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    void configuration() {
        var myConfig = new MyConfig();
        var bean1 = myConfig.bean1();
        var bean2 = myConfig.bean2();

        // bean1, bean2 factory method에서
        // 각각 common factory method를 호출해서 새로운 객체를 집어넣음
        // --> bean1.common과 bean2.common은 다름 (SingleTon 어김)
        Assertions.assertThat(bean1.common).isNotSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        // proxy 내부에서 common을 singleton으로 주입되도록 관리
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        var bean1 = myConfigProxy.bean1();
        var bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void configurationWithContext() {
        var ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        var bean1 = ac.getBean(Bean1.class);
        var bean2 = ac.getBean(Bean2.class);

        // Configuration의 Proxy Bin Methods가 True로 (default가 true임) 설정된 경우
        // 해당 class가 bean으로 등록될 시 proxy object를 앞에 두고 등록되는 방식
        // proxy로 인해 bean이 한 번만 생성되도록 보장한다.
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);

        // 내부에서 bean factory method를 호출하여 bean에 bean을 주입하는 경우가 아니라면
        // proxyBeanMethods를 false로 주어 proxy 생성에 대한 overhead를 줄이는 것이 좋음.
    }

    // Bean 1 <-- Common
    // Bean 2 <-- Common

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        // Constructor가 내부에서 단 하나만의 instance를 생성하도록 보장
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }

            return common;
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {
    }
}
