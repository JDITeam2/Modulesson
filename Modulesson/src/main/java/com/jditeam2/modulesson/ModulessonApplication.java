package com.jditeam2.modulesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ModulessonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModulessonApplication.class, args);
    }
}