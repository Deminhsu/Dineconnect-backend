package com.ken.taroserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


//@EnableSwagger2WebMvc
//@SpringBootApplication
//@Slf4j
@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableCaching//开发缓存注解功能
@EnableScheduling //开启任务调度
@ComponentScan(basePackages = {"com.ken.taroserver","com.ken.taropojo", "com.ken.tarocommon"})
public class TaroServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaroServerApplication.class, args);
        log.info("server start");
    }

}
