package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.TypedSerializer;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author potatolot
 */
public class JsonTypedSerializer implements TypedSerializer<String> {

    private static final String SEPARATOR = ",";
    private static final String OPEN_JSON = "{";
    private static final String CLOSE_JSON = "}";
    private final List<JsonFieldSerializer> fieldSerializersPipeline = List.of(
            new JsonStringFieldSerializer(),
            new JsonPrimitiveFieldSerializer(),
            new JsonDateFieldSerializer(),
            new JsonTemporalDateFieldSerializer());

    @Override
    public String serialize(Object object) {
        List<FieldObject> fieldObjects = buildFieldObjects(object);
        return new StringBuilder()
                .append(OPEN_JSON)
                .append(
                        fieldObjects.
                                stream()
                                .filter(fieldObject -> fieldObject.getValue() != null)
                                .map(this::buildJsonProperty)
                                .collect(Collectors.joining(SEPARATOR))
                )
                .append(CLOSE_JSON)
                .toString();
    }

    private String buildJsonProperty(FieldObject fieldObject) {
        Object data = fieldObject.getValue();
        for (JsonFieldSerializer jsonFieldSerializer : fieldSerializersPipeline) {
            if (jsonFieldSerializer.applies(fieldObject)) {
                data = jsonFieldSerializer.serialize(fieldObject, data);
            }
        }
        return String.valueOf(data);
    }

    private List<FieldObject> buildFieldObjects(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        return Stream.of(fields)
                .map(field -> buildFieldObject(field, object))
                .toList();
    }

    private FieldObject buildFieldObject(Field field, Object object) {
        try {
            Object value = FieldUtils.readField(field, object, true);
            return new FieldObject()
                    .elementName(field.getName())
                    .elementType(field.getType())
                    .annotation(List.of(field.getAnnotations()))
                    .value(value);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(JsonTypedSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
