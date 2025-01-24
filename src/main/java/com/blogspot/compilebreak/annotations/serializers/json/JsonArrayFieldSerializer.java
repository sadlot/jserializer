package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class JsonArrayFieldSerializer extends JsonFieldSerializer<Object[]> {
    private static final String SEPARATOR = ",";
    private static final String OPEN_JSON = "[";
    private static final String CLOSE_JSON = "]";

    @Override
    boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue().getClass().isArray();
    }

    @Override
    String serialize(FieldObject fieldObject, Object[] array) {
        return String.format("\"%s\":%s", getPropertyName(fieldObject), serializeArray(array));
    }

    private String serializeArray(Object[] array) {
        return new StringBuilder()
                .append(OPEN_JSON)
                .append(
                        String.join(SEPARATOR, buildArrayElements(array))
                )
                .append(CLOSE_JSON)
                .toString();
    }

    private List<String> buildArrayElements(Object[] array) {
        return Stream.of(array)
                .map(this::buildElement)
                .collect(Collectors.toList());
    }

    private String buildElement(Object object) {
        return object instanceof String ?
                String.format("\"%s\"", object) : String.format("%s", object);
    }
}
