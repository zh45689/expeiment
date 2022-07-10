package com.zh.bootStart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.zh.bootStart.dao")
public class ExperimentbybootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExperimentbybootApplication.class, args);
    }

}