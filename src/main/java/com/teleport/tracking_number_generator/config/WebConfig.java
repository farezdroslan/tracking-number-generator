package com.teleport.tracking_number_generator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final TimeConverter stringToOffsetDateTimeConverter;

    public WebConfig(TimeConverter converter) {
        this.stringToOffsetDateTimeConverter = converter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToOffsetDateTimeConverter);
    }
}
