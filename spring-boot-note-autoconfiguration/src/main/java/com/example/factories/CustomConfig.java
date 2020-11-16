package com.example.factories;

/**
 * @author Xiao
 */
public class CustomConfig {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FactConfig{" +
                "name='" + name + '\'' +
                '}';
    }
}
