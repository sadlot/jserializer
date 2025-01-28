package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonMapFieldSerializerTest {

    private final JsonMapFieldSerializer tested = new JsonMapFieldSerializer();

    @Test
    void shouldApplyForHashMap() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new HashMap<>());
        //when//then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForLinkedHashMap() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new LinkedHashMap<>());
        //when//then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyForArrayList() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new ArrayList<>());
        //when//then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeMapOfStrings() {
        //given
        Map<String, String> map = new LinkedHashMap<>();
        map.put("keyA", "valueA");
        map.put("keyB", "valueB");
        FieldObject fieldObject = new FieldObject()
                .value(map)
                .elementName("elements")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, map);

        //then
        assertEquals("\"elements\":{\"keyA\":\"valueA\",\"keyB\":\"valueB\"}", result);
    }

    @Test
    void shouldSerializeMapOfStringsWithCustomFieldName() {
        //given
        Map<String, String> map = new LinkedHashMap<>();
        map.put("keyA", "valueA");
        map.put("keyB", "valueB");
        JsonField field = mock(JsonField.class);
        FieldObject fieldObject = new FieldObject()
                .value(map)
                .elementName("elements")
                .annotation(List.of(field));

        //when
        when(field.name())
                .thenReturn("myMap");
        String result = tested.serialize(fieldObject, map);

        //then
        assertEquals("\"myMap\":{\"keyA\":\"valueA\",\"keyB\":\"valueB\"}", result);
    }

    @Test
    void shouldSerializeMapOfIntegers() {
        //given
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1, 100);
        map.put(2, 200);
        FieldObject fieldObject = new FieldObject()
                .value(map)
                .elementName("elements")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, map);

        //then
        assertEquals("\"elements\":{1:100,2:200}", result);
    }
}
