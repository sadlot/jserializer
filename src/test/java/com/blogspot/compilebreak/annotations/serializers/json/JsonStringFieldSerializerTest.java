package com.blogspot.compilebreak.annotations.serializers.json;


import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JsonStringFieldSerializerTest {

    private final JsonStringFieldSerializer tested = new JsonStringFieldSerializer();

    @Test
    void shouldApplyWhenString() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value("value");

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyWhenNotString() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(123);

        //when //then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeField() {
        //given
        JsonField annotation = Mockito.mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value("value")
                .elementName("element")
                .annotation(List.of(annotation));

        //when
        String result = tested.serialize(fieldObject, "value");
        //then
        assertEquals("\"element\":\"value\"", result);
    }

    @Test
    void shouldSerializeFieldWithoutAnnotation() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value("value")
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, "value");
        //then
        assertEquals("\"element\":\"value\"", result);
    }

    @Test
    void shouldSerializeFieldWithCustomFieldName() {
        //given
        JsonField annotation = Mockito.mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value("value")
                .elementName("element")
                .annotation(List.of(annotation));

        //when
        when(annotation.name())
                .thenReturn("customFieldName");
        String result = tested.serialize(fieldObject, "value");
        //then
        assertEquals("\"customFieldName\":\"value\"", result);
    }

    @Test
    void shouldSerializeFieldWithoutAnyAnnotation() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value("value")
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, "value");
        //then
        assertEquals("\"element\":\"value\"", result);
    }

}
