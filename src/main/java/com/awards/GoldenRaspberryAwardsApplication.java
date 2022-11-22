package com.awards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan("com.awards")
public class GoldenRaspberryAwardsApplication {

    public static void main(String[] args) {

        SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
    }

}
