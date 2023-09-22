package com.hugomarques.rinhabackend2023.pessoas;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(final List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(final String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : null;
    }
}
