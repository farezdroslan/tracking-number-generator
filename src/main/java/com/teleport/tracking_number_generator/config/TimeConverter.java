package com.teleport.tracking_number_generator.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class TimeConverter implements Converter<String, OffsetDateTime> {
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public OffsetDateTime convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }

        try {
            // Try standard ISO format first
            return OffsetDateTime.parse(source, ISO_FORMATTER);
        } catch (DateTimeParseException e) {
            // Handle space instead of '+' (e.g., "2018-11-20T19:29:32 08:00")
            String corrected = source.replace(" ", "+");
            return OffsetDateTime.parse(corrected, ISO_FORMATTER);
        }
    }
}
