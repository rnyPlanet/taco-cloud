package com.axelrod.tacocloud.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Configuration
public class AppConfiguration {

    @Component
    @ConfigurationProperties(prefix="taco.orders")
    @Data
    @Validated
    public static class OrderProps {
        @Min(value=5, message="must be between 5 and 25")
        @Max(value=25, message="must be between 5 and 25")
        private int pageSize = 20;
    }
}
