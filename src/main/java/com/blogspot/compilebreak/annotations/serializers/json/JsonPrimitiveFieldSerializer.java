package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.util.List;

/**
 * @author potatolot
 */
class JsonPrimitiveFieldSerializer extends JsonFieldSerializer<Object> {

    private static final List<Class> ACCEPTED_TYPES = List.of(Integer.class,
            Double.class,
            Short.class);

    @Override
    public boolean applies(FieldObject fieldObject) {
        return ACCEPTED_TYPES.contains(fieldObject.getValue().getClass());
    }

    @Override
    public String serialize(FieldObject fieldObject, Object value) {
        return new StringBuilder()
                .append("\"")
                .append(getPropertyName(fieldObject))
                .append("\":")
                .append(value)
                .toString();
    }

}
