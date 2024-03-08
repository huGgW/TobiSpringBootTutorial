package com.tutorial.tobispringboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/*
기본적으로 @Controller는 View를 반환하도록 한다. (JSP, Thymeleaf 등)
@RestController는 모든 method에 @ResponseBody를 붙이는 것과 같이 동작하여
View를 반환하지 않고, HTTP Response Body에 직접 데이터를 입력한다.
내부는 @Controller + @ResponseBody와 같다.
 */
@RestController
@RequestMapping("/hello")
public class HelloController implements ApplicationContextAware {
    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext = " + applicationContext);
        this.applicationContext = applicationContext;
    }
}
