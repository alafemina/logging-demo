package org.salesforce.insights.loggingdemo;

import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;


@RestController
@SpringBootApplication
public class LoggingDemoApplication {
    private static final Logger log = LoggerFactory.getLogger(LoggingDemoApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/")
    String index() {
        return "Welcome to Logging Demo!";
    }

    @RequestMapping("/service1")
    String hello() {
        log.info("Hit sample service 1");
        return "Service 1";
    }

    @RequestMapping("/redirect")
    String callBackToService1(HttpServletResponse httpResponse) {
        log.info("Called back to service 1");
        return restTemplate.getForObject("https://logging-demo.herokuapp.com/service1", String.class);
    }

    @RequestMapping("/service-error")
    void serviceError() {
        String traceId = MDC.get("X-B3-TraceId");
        Exception e = new Exception("Service exception at trace ID: " + traceId);
        log.error("Service error", e);
        Sentry.capture(e);
    }

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

}
