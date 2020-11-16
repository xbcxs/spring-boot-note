package com.example.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义自动装载类
 *
 * @author Xiao
 */
@Configuration
@EnableConfigurationProperties(value = CustomProperties.class)
public class CustomAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CustomAutoConfiguration.class);

    @Autowired
    private CustomProperties customProperties;

    @Bean
    CustomConfig factConfig() {
        CustomConfig factConfig = new CustomConfig();
        logger.info("======================>{}", customProperties.getName());
        factConfig.setName(customProperties.getName());
        return factConfig;
    }
}
