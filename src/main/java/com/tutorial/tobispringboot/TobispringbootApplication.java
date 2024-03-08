package com.tutorial.tobispringboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

import static com.tutorial.tobispringboot.MySpringApplication.run;

// Configuration annotation: 이 class는 Spring Container의 구성 정보를 가지고 있는 class이다!
// Spring Container에 Configuration이 붙은 class가 처음으로 등록되어,
// 전체 애플리케이션 실행에 필요한 중요한 정보들도 함께 등록한다.
@Configuration
// ComponentScan annotation: 이 class가 속한 package를 기준으로 하위 package를 탐색하여
// @Component가 붙은 class를 찾아서 Spring Container에 bean으로 등록한다.
@ComponentScan //
public class TobispringbootApplication {
	@Bean
	public ServletWebServerFactory servletWebServletFactory() {
		return new TomcatServletWebServerFactory();
	}

	/*
	명시적으로 ApplicationContext를 주입해주지 않아도 동작.
	DispatcherServlet은 ApplicationContextAware를 구현하고 있고,
	이를 구현한 class는 빈으로 등록될 시 applicationContext가 자동으로 주입된다.
	이는 Spring Container가 초기화될 시 발생한다.

	ApplicationContext를 주입 받는 2가지 방법:
	1. ApplicationContextAware interface를 구현하는 class를 빈으로 등록한다.
	2. 생성자 parameter로 ApplicationContext를 받는다. (ApplicationContext도 하나의 bean 취급 하여 가능)
	 */
	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	// Bean annotation: 이 method는 bean을 생성하기 위한 Factory Method이다!!
//	@Bean
//	public HelloController helloController(HelloService helloService) {
//		return new HelloController(helloService);
//	}
//
//	@Bean
//	public HelloService helloService() {
//		return new SimpleHelloService();
//	}

	public static void main(String[] args) {
		MySpringApplication.run(TobispringbootApplication.class, args);
	}

}
