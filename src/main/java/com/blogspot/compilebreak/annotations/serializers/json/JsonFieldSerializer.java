package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

/**
 * @author potatolot
 */
abstract class JsonFieldSerializer<T> {

    abstract boolean applies(FieldObject fieldObject);

    abstract String serialize(FieldObject fieldObject, T value);

    protected String buildField(String name, String value) {
        return String.format("\"%s\":\"%s\"", name, value);
    }

    protected String getPropertyName(FieldObject fieldObject) {
        return fieldObject.getAnnotations()
                .stream()
                .filter(annotation -> annotation instanceof JsonField)
                .findFirst()
                .map(JsonField.class::cast)
                .map(JsonField::name)
                .orElse(fieldObject.getElementName());
    }
}
