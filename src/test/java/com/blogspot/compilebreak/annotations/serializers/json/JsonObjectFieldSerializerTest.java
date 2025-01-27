package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonObjectFieldSerializerTest {

    private final JsonObjectFieldSerializer tested = new JsonObjectFieldSerializer();

    @Test
    void shouldApplyWhenJsonDocument() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new TestObject());

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyWhenNotJsonDocument() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new Object());

        //when //then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeEmptyObject() {
        //given
        FieldObject fieldObject = new FieldObject()
                .value(new TestObject())
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, new TestObject());

        //then
        assertEquals("\"element\":{\"counter\":0}", result);
    }

    @Test
    void shouldSerializeWithSubObject() {
        //given
        TestObject testObject = new TestObject();
        OtherTestObject otherTestObject = new OtherTestObject();
        otherTestObject.setMyString("test");
        testObject.setOtherTestObject(otherTestObject);
        FieldObject fieldObject = new FieldObject()
                .value(testObject)
                .elementName("element")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, testObject);

        //then
        assertEquals("\"element\":{\"counter\":0,\"otherTestObject\":{\"myString\":\"test\"}}", result);
    }

    @Test
    void shouldSerializeWithSubObjectAndCustomName() {
        //given
        TestObject testObject = new TestObject();
        OtherTestObject otherTestObject = new OtherTestObject();
        otherTestObject.setMyString("test");
        testObject.setOtherTestObject(otherTestObject);

        JsonField jsonField = mock(JsonField.class);

        FieldObject fieldObject = new FieldObject()
                .value(testObject)
                .elementName("element")
                .annotation(List.of(jsonField));

        //when
        when(jsonField.name())
                .thenReturn("customName");
        String result = tested.serialize(fieldObject, testObject);

        //then
        assertEquals("\"customName\":{\"counter\":0,\"otherTestObject\":{\"myString\":\"test\"}}", result);
    }

}
