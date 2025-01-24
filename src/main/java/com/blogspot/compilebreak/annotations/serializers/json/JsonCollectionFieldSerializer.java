package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class JsonCollectionFieldSerializer extends JsonFieldSerializer<Collection<?>> {
    private static final String SEPARATOR = ",";
    private static final String OPEN_JSON = "[";
    private static final String CLOSE_JSON = "]";

    @Override
    boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof Collection;
    }

    @Override
    String serialize(FieldObject fieldObject, Collection<?> value) {
        return String.format("\"%s\":%s", getPropertyName(fieldObject), serializeCollection(value));
    }

    private String serializeCollection(Collection<?> value) {
        return new StringBuilder()
                .append(OPEN_JSON)
                .append(
                        String.join(SEPARATOR, buildCollectionElements(value))
                )
                .append(CLOSE_JSON)
                .toString();
    }

    private List<String> buildCollectionElements(Collection<?> value) {
        return value.stream()
                .map(this::buildElement)
                .collect(Collectors.toList());
    }

    private String buildElement(Object object) {
        return object instanceof String ?
                String.format("\"%s\"", object) : String.format("%s", object);
    }
}
