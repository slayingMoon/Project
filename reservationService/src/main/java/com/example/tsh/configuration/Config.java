package com.example.tsh.configuration;


import com.example.tsh.util.GenericMapperImpl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;


@Configuration
@ComponentScan
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
//    @Bean
//    public Validator validator (final AutowireCapableBeanFactory autowireCapableBeanFactory) {
//
//        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
//                .configure().constraintValidatorFactory(new SpringConstraintValidatorFactory(autowireCapableBeanFactory))
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//
//        return validator;
//    }
    @Bean
    public Validator defaultValidator() {
        return new LocalValidatorFactoryBean();
    }
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(defaultValidator());
        return methodValidationPostProcessor;
    }
}
