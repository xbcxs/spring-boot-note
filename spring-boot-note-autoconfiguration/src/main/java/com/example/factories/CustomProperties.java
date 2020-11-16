package com.example.factories;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Xiao
 */
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FactProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
