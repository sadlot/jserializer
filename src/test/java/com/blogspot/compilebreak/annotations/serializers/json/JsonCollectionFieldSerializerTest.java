package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonCollectionFieldSerializerTest {

    private final JsonCollectionFieldSerializer tested = new JsonCollectionFieldSerializer();

    @Test
    void shouldApplyForArrayList(){
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new ArrayList<>());
        //when//then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForLinkedListList(){
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new LinkedList<>());
        //when//then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForHashSet(){
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new HashSet<>());
        //when//then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyForHashMap(){
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new HashMap<>());
        //when//then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeListOfStrings() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(List.of("a", "b", "c"))
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, List.of("a", "b", "c"));

        //then
        assertEquals("\"element\":[\"a\",\"b\",\"c\"]", result);
    }

    @Test
    void shouldSerializeListOfStringsWithCustomFieldName() {
        //given
        JsonField jsonField = mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value(List.of("a", "b", "c"))
                .elementName("element")
                .annotation(List.of(jsonField));

        //when
        when(jsonField.name())
                .thenReturn("myList");
        String result = tested.serialize(fieldObject, List.of("a", "b", "c"));

        //then
        assertEquals("\"myList\":[\"a\",\"b\",\"c\"]", result);
    }

    @Test
    void shouldSerializeListOfIntegers() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(List.of(1, 2, 3))
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, List.of(1, 2, 3));

        //then
        assertEquals("\"element\":[1,2,3]", result);
    }

    @Test
    void shouldSerializeSetOfDoubles() {
        //given
        LinkedHashSet<Double> set = new LinkedHashSet<>();
        set.add(0.1d);
        set.add(0.2d);
        set.add(0.3d);
        FieldObject fieldObject = new FieldObject()
                .value(set)
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, set);

        //then
        assertEquals("\"element\":[0.1,0.2,0.3]", result);
    }

}
