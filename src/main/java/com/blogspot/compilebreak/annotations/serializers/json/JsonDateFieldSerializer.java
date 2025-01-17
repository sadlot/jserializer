package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import java.util.Date;

/**
 *
 * @author potatolot
 */
public class JsonDateFieldSerializer implements JsonFieldSerializer<Date> {

    @Override
    public String serialize(FieldObject fieldObject, Date value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean applies(FieldObject fieldObject) {
        return fieldObject.getAnnotations()
                .stream()
                .anyMatch(annotation -> annotation instanceof JsonDateField);
    }

}
