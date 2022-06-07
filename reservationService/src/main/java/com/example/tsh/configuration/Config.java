package com.example.tsh.configuration;


import com.example.tsh.util.GenericMapperImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    GenericMapperImpl reservationGenericMapper()
    {
        return new GenericMapperImpl<>();

    }

    @Bean
    ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
