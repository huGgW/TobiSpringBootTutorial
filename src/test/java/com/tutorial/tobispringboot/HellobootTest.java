package com.tutorial.tobispringboot;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= TobispringbootApplication.class)
@TestPropertySource("classpath:/application.properties")
// Test에서는 transactional을 붙이면,
// test를 하나의 transaction으로 묶고,
// test가 끝난 후 해당 transaction을 롤백하도록 동작한다.
@Transactional
public @interface HellobootTest {
}
