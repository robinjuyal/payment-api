package com.paymentApi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
//@EnableJpaRepositories(basePackages = "com.paymentApi.repository")
//@EntityScan(basePackages = "com.paymentApi.entity")
@EnableTransactionManagement
public class DatabaseConfig {
    
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
