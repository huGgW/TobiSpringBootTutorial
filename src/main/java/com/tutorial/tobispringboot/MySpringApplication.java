package com.tutorial.tobispringboot;

import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        // Bean class를 기반으로 Spring Container안에 bean을 등록하는 방식
		/*
		HelloController가 생성될 때,
		Spring Container는 HelloService 타입의 Bean을 찾아서 주입한다.
		순서를 고려할 필요가 없다.
		 */
//		var applicationContext = new GenericWebApplicationContext() {
//			......
//		}
//		applicationContext.registerBean(HelloController.class);
//		applicationContext.registerBean(SimpleHelloService.class);

        // Configuration, Bean annotation을 기반으로 Spring Container안에 bean 구성 정보를 등록하는 방식
//		var applicationContext = new AnnotationConfigWebApplicationContext() {
//			@Override
//			protected void onRefresh() {
//				super.onRefresh();
//
//				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//				var webServer = serverFactory.getWebServer(servletContext -> {
//					servletContext.addServlet("dispatcherServlet",
//							// DispatcherServlet이 applicationContext 안의 적절한 bean을 찾아서 요청을 전달한다.
//							new DispatcherServlet(this)
//					).addMapping("/*");
//				});
//				webServer.start();
//			}
//		};
//		applicationContext.register(TobispringbootApplication.class);
//		applicationContext.refresh();


        var applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//				dispatcherServlet.setApplicationContext(this);

                var webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            dispatcherServlet
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh();
    }
}
