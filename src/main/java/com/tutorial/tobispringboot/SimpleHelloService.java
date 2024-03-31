package com.tutorial.tobispringboot;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
