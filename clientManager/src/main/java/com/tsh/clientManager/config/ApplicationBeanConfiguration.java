package com.tsh.clientManager.config;

import com.tsh.clientManager.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtil();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
