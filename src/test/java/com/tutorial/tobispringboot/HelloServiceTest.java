package com.tutorial.tobispringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        var helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        var decorator = new HelloDecorator(name -> name);

        var ret = decorator.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }
}
