/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

/**
 *
 * @author potatolot
 */
class JsonStringFieldSerializer implements JsonFieldSerializer<String> {

    @Override
    public boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof String;
    }

    @Override
    public String serialize(FieldObject fieldObject, String value) {
        return buildField(getPropertyName(fieldObject), value);
    }

    private String getPropertyName(FieldObject fieldObject) {
        return fieldObject.getAnnotations()
                .stream()
                .filter(annotation -> annotation instanceof JsonField)
                .findFirst()
                .map(JsonField.class::cast)
                .map(JsonField::name)
                .orElse(fieldObject.getElementName());
    }

}
