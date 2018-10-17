package com.spider.gbwchina;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.spider.gbwchina.dao")
public class GbwChinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbwChinaApplication.class, args);
    }
}
