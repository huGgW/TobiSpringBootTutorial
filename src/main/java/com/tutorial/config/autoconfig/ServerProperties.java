package com.tutorial.config.autoconfig;

import com.tutorial.config.MyConfigurationProperties;
import org.springframework.stereotype.Component;

import static org.apache.naming.SelectorContext.prefix;

// Property를 저장한 class를 따로 생성
// 1. Property를 반복되는 코드 없이 여러 곳에서 재사용 가능
// 2. field가 많아질 경우 복잡해질 수 있음
@MyConfigurationProperties(prefix = "server") // server.contextPath, server.port
public class ServerProperties {
    private String contextPath;
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
