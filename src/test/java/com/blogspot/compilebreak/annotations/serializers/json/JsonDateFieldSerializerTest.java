package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JsonDateFieldSerializerTest {

    private final JsonDateFieldSerializer tested = new JsonDateFieldSerializer();

    @Test
    void shouldApplyWhenDate() {
        //given
        Date date = new Date(1737642443494L);
        FieldObject fieldObject = new FieldObject()
                .value(date);

        //when //then
        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldNotApplyWhenOtherObject() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value("string");

        //when //then
        assertFalse(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeFieldWithDefaultFormat() {
        //given
        JsonDateField annotation = Mockito.mock(JsonDateField.class);
        Date date = new Date(1737642443494L);
        FieldObject fieldObject = new FieldObject()
                .value(date)
                .elementName("date")
                .annotation(List.of(annotation));

        //when
        String result = tested.serialize(fieldObject, date);
        //then
        assertEquals("\"date\":\"2025-01-23\"", result);
    }

    @Test
    void shouldSerializeFieldWithCustomFormat() {
        //given
        JsonDateField annotation = Mockito.mock(JsonDateField.class);
        Date date = new Date(1737642443494L);
        FieldObject fieldObject = new FieldObject()
                .value(date)
                .elementName("date")
                .annotation(List.of(annotation));

        //when
        when(annotation.format())
                .thenReturn("YYYY-MM-DD HH:mm:SS");
        String result = tested.serialize(fieldObject, date);
        //then
        assertEquals("\"date\":\"2025-01-23 15:27:494\"", result);
    }

    @Test
    void shouldSerializeFieldWithCustomFormatAndName() {
        //given
        JsonDateField dateAnnotation = Mockito.mock(JsonDateField.class);
        JsonField nameAnnotation = Mockito.mock(JsonField.class);
        Date date = new Date(1737642443494L);
        FieldObject fieldObject = new FieldObject()
                .value(date)
                .elementName("date")
                .annotation(List.of(dateAnnotation, nameAnnotation));

        //when
        when(dateAnnotation.format())
                .thenReturn("YYYY-MM-DD HH:mm:SS");
        when(nameAnnotation.name())
                .thenReturn("myDate");
        String result = tested.serialize(fieldObject, date);
        //then
        assertEquals("\"myDate\":\"2025-01-23 15:27:494\"", result);
    }
}
