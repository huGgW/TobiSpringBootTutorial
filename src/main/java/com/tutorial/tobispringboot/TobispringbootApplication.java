package com.tutorial.tobispringboot;

import com.tutorial.config.MySpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;


@MySpringBootApplication
public class TobispringbootApplication {
	private final JdbcTemplate jdbcTemplate;

    public TobispringbootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	// InitializingBean interface를 구현하면 bean이 모든 property setting까지 다 끝난 후
	// afterProperties method를 호출해준다.

	// 해당 postconstruct는 java 표준 기능으로
	// Spring Container가 해당 annoation이 붙은 method를 bean 준비가 끝난 후 실행시켜준다.
	// InitializingBean의 간결한 대체로 많이 사용
    @PostConstruct
	void init() {
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args) {
		SpringApplication.run(TobispringbootApplication.class, args);
	}
}
