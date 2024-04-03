package com.tutorial.tobispringboot;

import com.tutorial.config.MySpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@MySpringBootApplication
public class TobispringbootApplication {

//	// ApplicationRunner functional interface를 구현한 bean은
//	// Spring boot의 모든 초기화 작업이 끝난 다음 run이 실행됨!
//	@Bean
//	ApplicationRunner applicationRunner(Environment env) {
//		// property 설정 우선순위
//		// 1. System property (JVM 변수 설정, -Dkey=val)
//		// 2. System Environment Variable 설정 (OS 환경변수)
//		// 3. spring boot property 설정 (application.*)
//
//		return args -> {
//			String name = env.getProperty("my.name");
//			System.out.println("my.name: " + name);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(TobispringbootApplication.class, args);
	}
}
