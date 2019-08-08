package org.salesforce.insights.loggingdemo;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.logging.log4j.Logger;

import java.util.Map;

@Controller
@SpringBootApplication
public class LoggingDemoApplication {
    private static Logger log = LogManager.getLogger(LoggingDemoApplication.class);

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/service1")
    String hello(Map<String, Object> model) {
        log.info("Hit sample service 1");
        return "service1";
    }

    public static void main(String[] args) {
        SpringApplication.run(LoggingDemoApplication.class, args);
    }

}
