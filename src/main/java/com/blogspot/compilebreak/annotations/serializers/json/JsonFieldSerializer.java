package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

/**
 *
 * @author potatolot
 */
interface JsonFieldSerializer<T> {

    boolean applies(FieldObject fieldObject);

    String serialize(FieldObject fieldObject, T value);

    default String
            buildField(String name, String value) {
        return String.format("\"%s\":\"%s\"", name, value);
    }
}
