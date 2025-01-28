package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class JsonMapFieldSerializer extends JsonFieldSerializer<Map<?, ?>> {
    private static final String SEPARATOR = ",";
    private static final String OPEN_JSON = "{";
    private static final String CLOSE_JSON = "}";

    @Override
    boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof Map;
    }

    @Override
    String serialize(FieldObject fieldObject, Map<?, ?> value) {
        return String.format("\"%s\":%s", getPropertyName(fieldObject), serializeMap(value));
    }

    private String serializeMap(Map<?, ?> value) {
        return new StringBuilder()
                .append(OPEN_JSON)
                .append(
                        String.join(SEPARATOR, buildMapElements(value))
                )
                .append(CLOSE_JSON)
                .toString();
    }

    private List<String> buildMapElements(Map<?, ?> value) {
        return value.entrySet()
                .stream()
                .map(this::buildElement)
                .collect(Collectors.toList());
    }

    private String buildElement(Map.Entry<?, ?> object) {
        String key = buildString(object.getKey());
        String value = buildString(object.getValue());
        return String.format("%s:%s", key, value);
    }

    private String buildString(Object object) {
        return object instanceof String ?
                String.format("\"%s\"", object) : String.format("%s", object);
    }
}
