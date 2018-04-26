package com.i000.stock.user.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 10:22 2018/4/23
 * @Modified By:
 */
@EnableScheduling
@EnableCaching
@EnableFeignClients(basePackages = "com.i000.stock*")
@SpringBootApplication(scanBasePackages = "com.i000.stock*")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
