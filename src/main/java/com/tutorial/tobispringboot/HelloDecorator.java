package com.tutorial.tobispringboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
// 동일한 bean이 여러 개 존재할 때 우선적으로 선택하라
// Decorator 등 여러 bean을 정의할 경우 java 코드로 명시적으로 하는 것이 나을 수 있음.
@Primary
public class HelloDecorator implements HelloService {
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
