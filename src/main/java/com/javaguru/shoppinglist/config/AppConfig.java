package com.javaguru.shoppinglist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppinglist")
@Import({ConsoleUiConfig.class, ValidationServiceConfig.class})
public class AppConfig {

}
