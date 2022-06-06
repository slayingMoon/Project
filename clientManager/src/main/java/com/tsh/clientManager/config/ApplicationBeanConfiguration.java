package com.tsh.clientManager.config;

import com.tsh.clientManager.util.ValidationUtil;
import com.tsh.clientManager.util.impl.ValidationUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
