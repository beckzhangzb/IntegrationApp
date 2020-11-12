package com.job.cannal.datasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by haha on 2020/8/21.
 */
@SpringBootApplication
@EnableScheduling
public class DatasyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatasyncApplication.class, args);
    }
}
