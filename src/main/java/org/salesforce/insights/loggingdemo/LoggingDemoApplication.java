package org.salesforce.insights.loggingdemo;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.apache.logging.log4j.Logger;


@RestController
@SpringBootApplication
public class LoggingDemoApplication {
    private static Logger log = LogManager.getLogger(LoggingDemoApplication.class);
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

    @RequestMapping("/salesforce")
    String callBackToService1() {
        log.info("Called back to Salesforce");
        return restTemplate.getForObject("https://logging-demo.herokuapp.com/service1", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

}
