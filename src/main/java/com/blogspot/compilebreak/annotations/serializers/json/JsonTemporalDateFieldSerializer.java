package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Optional;

class JsonTemporalDateFieldSerializer extends JsonFieldSerializer<Temporal> {

    private static final String DEFAULT_DATE_FORMAT = "YYYY-MM-dd";
    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    @Override
    boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof Temporal;
    }

    @Override
    String serialize(FieldObject fieldObject, Temporal value) {
        return buildField(getPropertyName(fieldObject), formatDate(fieldObject, value));
    }

    private String formatDate(FieldObject fieldObject, Temporal value) {
        String format = getDateFormat(fieldObject);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(value);
    }

    private String getDateFormat(FieldObject fieldObject) {
        return getJsonDateFieldAnnotation(fieldObject)
                .map(JsonDateField::format)
                .orElse(getDefaultDateFormat(fieldObject));
    }

    private Optional<JsonDateField> getJsonDateFieldAnnotation(FieldObject fieldObject) {
        return fieldObject.getAnnotations()
                .stream()
                .filter(annotation -> annotation instanceof JsonDateField)
                .map(JsonDateField.class::cast)
                .findFirst();
    }

    private String getDefaultDateFormat(FieldObject fieldObject) {
        if (fieldObject.getValue() instanceof LocalTime || fieldObject.getValue() instanceof OffsetTime) {
            return DEFAULT_TIME_FORMAT;
        }
        return DEFAULT_DATE_FORMAT;
    }
}
