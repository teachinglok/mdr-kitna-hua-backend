package com.zepiox.mdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MdrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdrApplication.class, args);
    }

}
