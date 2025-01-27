package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDocument;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

class JsonObjectFieldSerializer extends JsonFieldSerializer<Object> {
    private static final JsonTypedSerializer JSON_TYPED_SERIALIZER = new JsonTypedSerializer();

    @Override
    boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue().getClass().isAnnotationPresent(JsonDocument.class);
    }

    @Override
    String serialize(FieldObject fieldObject, Object value) {
        return String.format("\"%s\":%s", getPropertyName(fieldObject), JSON_TYPED_SERIALIZER.serialize(value));
    }
}
