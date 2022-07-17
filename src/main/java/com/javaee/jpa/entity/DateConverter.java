package com.javaee.jpa.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class DateConverter implements AttributeConverter<String, Timestamp> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(String entityValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(entityValue, formatter);
        return entityValue == null ? null : java.sql.Timestamp.valueOf(date.atStartOfDay());
    }

    @Override
    public String convertToEntityAttribute(java.sql.Timestamp dbValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dbValue == null ? "" : dbValue.toLocalDateTime().toLocalDate().format(formatter);
    }
}
