package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonArrayFieldSerializerTest {

    private final JsonArrayFieldSerializer tested = new JsonArrayFieldSerializer();

    @Test
    void shouldApplyWhenArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new String[]{"a", "b", "c"});

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyWhenNotArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new Object());

        //when //then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeStringArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new String[]{"a", "b", "c"})
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, new String[]{"a", "b", "c"});

        //then
        assertEquals("\"element\":[\"a\",\"b\",\"c\"]", result);
    }

    @Test
    void shouldSerializeStringArrayWithCustomFieldName() {
        //given

        JsonField jsonField = mock(JsonField.class);
        FieldObject fieldObject = new FieldObject()
                .value(new String[]{"a", "b", "c"})
                .elementName("element")
                .annotation(List.of(jsonField));

        //when
        when(jsonField.name())
                .thenReturn("myArray");
        String result = tested.serialize(fieldObject, new String[]{"a", "b", "c"});

        //then
        assertEquals("\"myArray\":[\"a\",\"b\",\"c\"]", result);
    }

    @Test
    void shouldSerializeIntegerArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new Integer[]{1, 2, 3})
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, new Integer[]{1, 2, 3});

        //then
        assertEquals("\"element\":[1,2,3]", result);
    }

    @Test
    void shouldSerializeShortArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new Short[]{1, 2, 3})
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, new Short[]{1, 2, 3});

        //then
        assertEquals("\"element\":[1,2,3]", result);
    }

    @Test
    void shouldSerializeObjectArray() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new Object[]{1, "2", 0.3d})
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, new Object[]{1, "2", 0.3});

        //then
        assertEquals("\"element\":[1,\"2\",0.3]", result);
    }

}
