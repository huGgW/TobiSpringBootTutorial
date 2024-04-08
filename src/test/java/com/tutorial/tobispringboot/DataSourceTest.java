package com.tutorial.tobispringboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TobispringbootApplication.class)
// properties file에서 불러오는 것은 Spring Boot의 동작 방식이지 Spring의 기본 동작 방식은 아님
// 따라서 해당 annotation을 통해 property를 추가해줌
@TestPropertySource("classpath:/application.properties")
public class DataSourceTest {
    @Autowired DataSource dataSource;

    @Test
    void connect() throws SQLException {
        var connection = dataSource.getConnection();
        connection.close();
    }
}
