package com.example.tsh;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class TshApplication {


    public static void main(String[] args) {
        System.out.println("Hello brat");
        SpringApplication.run(TshApplication.class, args);

    }

}
