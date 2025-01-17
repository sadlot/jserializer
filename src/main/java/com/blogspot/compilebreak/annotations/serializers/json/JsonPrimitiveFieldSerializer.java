package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import java.util.List;

/**
 *
 * @author potatolot
 */
class JsonPrimitiveFieldSerializer implements JsonFieldSerializer<Object> {

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
                .append(String.valueOf(value))
                .toString();
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
