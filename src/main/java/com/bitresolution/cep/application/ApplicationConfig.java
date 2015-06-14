package com.bitresolution.cep.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.config.SiddhiConfiguration;

@Configuration
@ComponentScan(basePackageClasses = {ApplicationConfig.class})
public class ApplicationConfig {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public SiddhiConfiguration siddhiConfiguration() {
        return new SiddhiConfiguration();
    }

    @Bean
    public SiddhiManager siddhiManager(SiddhiConfiguration siddhiConfiguration) {
        return new SiddhiManager(siddhiConfiguration);
    }
}
