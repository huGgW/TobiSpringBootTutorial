package com.tutorial.tobispringboot;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@MyComponent
public class SimpleHelloService implements HelloService {
    private final ApplicationContext applicationContext;

    public SimpleHelloService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println("applicationContext = " + applicationContext);
        System.out.println("SimpleHelloService 생성");
    }

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
