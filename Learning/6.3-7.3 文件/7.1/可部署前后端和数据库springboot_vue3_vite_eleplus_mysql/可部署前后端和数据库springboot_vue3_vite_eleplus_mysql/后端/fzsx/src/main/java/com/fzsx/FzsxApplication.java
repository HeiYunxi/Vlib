package com.fzsx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fzsx.mapper")
public class FzsxApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzsxApplication.class, args);
    }

}
