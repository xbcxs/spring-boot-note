package com.xbcxs.fileupload.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Xiao
 */
@Configuration
@EnableConfigurationProperties(value = FileServerProperties.class)
public class FileServerAutoConfiguration {

    @Autowired
    FileServerProperties fileServerProperties;

    @Bean
    FileServerConfig fileServerConfig() {
        FileServerConfig fileServerConfig = new FileServerConfig();
        fileServerConfig.setStorage(fileServerProperties.getPath() + File.separator + "storage");
        fileServerConfig.setStorageTemp(fileServerProperties.getPath() + File.separator + "temp");
        return fileServerConfig;
    }
}
