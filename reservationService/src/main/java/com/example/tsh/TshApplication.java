package com.example.tsh;

import com.example.tsh.service.impl.OpenFolderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableScheduling
public class TshApplication {

@Autowired
private OpenFolderServiceImpl openFolderService;
    public static void main(String[] args) {
        System.out.println("Hello brat");
        SpringApplication.run(TshApplication.class, args);

    }

}
