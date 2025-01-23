package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;

/**
 * @author potatolot
 */
class JsonStringFieldSerializer extends JsonFieldSerializer<String> {

    @Override
    public boolean applies(FieldObject fieldObject) {
        return fieldObject.getValue() instanceof String;
    }

    @Override
    public String serialize(FieldObject fieldObject, String value) {
        return buildField(getPropertyName(fieldObject), value);
    }

}
