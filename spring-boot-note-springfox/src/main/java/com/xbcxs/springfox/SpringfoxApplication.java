package com.xbcxs.springfox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class SpringfoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringfoxApplication.class, args);
    }

}
