package com.spring.payment.util.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();
        return mapper;
    }

}
