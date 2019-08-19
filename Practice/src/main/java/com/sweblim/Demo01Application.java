package com.sweblim;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Demo01Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
