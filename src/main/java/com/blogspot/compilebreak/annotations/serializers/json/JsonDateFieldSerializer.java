package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class JsonDateFieldSerializer extends JsonFieldSerializer<Date> {

    private static final String DEFAULT_DATE_FORMAT = "YYYY-MM-DD";

    @Override
    public String serialize(FieldObject fieldObject, Date value) {
        return buildField(getPropertyName(fieldObject), formatDate(fieldObject, value));
    }

    @Override
    public boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof Date;
    }

    private String formatDate(FieldObject fieldObject, Date value) {
        String format = getDateFormat(fieldObject);
        Format formatter = new SimpleDateFormat(format);
        return formatter.format(value);
    }

    private String getDateFormat(FieldObject fieldObject) {
        return getJsonDateFieldAnnotation(fieldObject)
                .map(JsonDateField::format)
                .orElse(DEFAULT_DATE_FORMAT);
    }

    private Optional<JsonDateField> getJsonDateFieldAnnotation(FieldObject fieldObject) {
        return fieldObject.getAnnotations()
                .stream()
                .filter(annotation -> annotation instanceof JsonDateField)
                .map(JsonDateField.class::cast)
                .findFirst();
    }

}
