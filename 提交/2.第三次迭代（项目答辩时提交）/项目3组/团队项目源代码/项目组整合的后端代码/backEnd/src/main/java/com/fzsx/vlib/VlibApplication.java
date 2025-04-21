package com.fzsx.vlib;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fzsx.vlib.mapper")
public class VlibApplication {

    public static void main(String[] args) {
        SpringApplication.run(VlibApplication.class, args);
    }

}
