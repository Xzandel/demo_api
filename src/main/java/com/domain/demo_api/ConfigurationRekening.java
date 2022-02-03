package com.domain.demo_api;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationRekening {
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
