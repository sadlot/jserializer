package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JsonPrimitiveFieldSerializerTest {

    private final JsonPrimitiveFieldSerializer tested = new JsonPrimitiveFieldSerializer();

    @Test
    void shouldApplyWhenInt() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(123);

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyWhenInteger() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(Integer.valueOf(123));

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyWhenShort() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value((short) 1);

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyWhenDouble() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(1.0d);

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyWhenNotString() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value("123");

        //when //then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeField() {
        //given
        JsonField annotation = Mockito.mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value(123)
                .elementName("element")
                .annotation(List.of(annotation));

        //when
        String result = tested.serialize(fieldObject, 123);
        //then
        assertEquals("\"element\":123", result);
    }

    @Test
    void shouldSerializeFieldWithoutAnnotation() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(123)
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, 123);
        //then
        assertEquals("\"element\":123", result);
    }

    @Test
    void shouldSerializeFieldWithCustomFieldName() {
        //given
        JsonField annotation = Mockito.mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value(123)
                .elementName("element")
                .annotation(List.of(annotation));

        //when
        when(annotation.name())
                .thenReturn("customFieldName");
        String result = tested.serialize(fieldObject, 123);
        //then
        assertEquals("\"customFieldName\":123", result);
    }

    @Test
    void shouldSerializeFieldWithoutAnyAssociations() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(123)
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, 123);
        //then
        assertEquals("\"element\":123", result);
    }
}
