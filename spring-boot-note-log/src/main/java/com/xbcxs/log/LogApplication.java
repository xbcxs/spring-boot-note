package com.xbcxs.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogApplication {

    private static Logger log = LoggerFactory.getLogger(LogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
        log.debug("I am debug!!!");
        log.info("I am info!!!");
        log.warn("I am warn!!!");
        log.error("I am error!!!");
    }

}
