package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.mapping.JsonField;
import com.blogspot.compilebreak.annotations.serializers.json.model.FieldObject;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JsonTemporalDateFieldSerializerTest {

    private final JsonTemporalDateFieldSerializer tested = new JsonTemporalDateFieldSerializer();

    @Test
    void shouldApplyForLocalDateTime() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(LocalDateTime.now());
        //when//then

        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForLocalDate() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(LocalDate.now());
        //when//then

        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForOffsetDateTime() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(OffsetDateTime.now());
        //when//then

        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldApplyForZonedDateTime() {
        //given

        FieldObject fieldObject = new FieldObject()
                .value(ZonedDateTime.now());
        //when//then

        assertTrue(tested.applies(fieldObject));
    }

    @Test
    void shouldSerializeForLocalDateTime() {
        //given

        LocalDateTime localDateTime = LocalDateTime.of(2025, 1, 23, 16, 42);
        FieldObject fieldObject = new FieldObject()
                .value(localDateTime)
                .elementName("dateTime")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, localDateTime);
        //then
        assertEquals("\"dateTime\":\"2025-01-23\"", result);
    }

    @Test
    void shouldSerializeForZonedDateTime() {
        //given

        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                LocalDateTime.of(2025, 1, 23, 16, 42),
                ZoneOffset.UTC);
        FieldObject fieldObject = new FieldObject()
                .value(zonedDateTime)
                .elementName("dateTime")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, zonedDateTime);
        //then
        assertEquals("\"dateTime\":\"2025-01-23\"", result);
    }

    @Test
    void shouldSerializeForLocalTime() {
        //given

        LocalTime localTime = LocalTime.of(16, 42);
        FieldObject fieldObject = new FieldObject()
                .value(localTime)
                .elementName("localTime")
                .annotation(List.of());

        //when
        String result = tested.serialize(fieldObject, localTime);
        //then
        assertEquals("\"localTime\":\"16:42:00\"", result);
    }

    @Test
    void shouldSerializeForOffsetTime() {
        //given
        JsonDateField dateAnnotation = mock(JsonDateField.class);
        OffsetTime localTime = OffsetTime.of(LocalTime.of(16, 42), ZoneOffset.UTC);
        FieldObject fieldObject = new FieldObject()
                .value(localTime)
                .elementName("localTime")
                .annotation(List.of(dateAnnotation));

        //when
        when(dateAnnotation.format())
                .thenReturn("HH:mm");
        String result = tested.serialize(fieldObject, localTime);
        //then
        assertEquals("\"localTime\":\"16:42\"", result);
    }

    @Test
    void shouldSerializeForLocalDateTimeWithJsonFieldAnnotation() {
        //given
        JsonField annotation = mock(JsonField.class);
        LocalDateTime localDateTime = LocalDateTime.of(2025, 1, 23, 16, 42);
        FieldObject fieldObject = new FieldObject()
                .value(localDateTime)
                .elementName("dateTime")
                .annotation(List.of(annotation));

        //when
        when(annotation.name())
                .thenReturn("myDateTime");
        String result = tested.serialize(fieldObject, localDateTime);
        //then
        assertEquals("\"myDateTime\":\"2025-01-23\"", result);
    }

    @Test
    void shouldSerializeForLocalDateTimeWithJsonFieldAndDateAnnotation() {
        //given
        JsonField jsonFieldAnnotation = mock(JsonField.class);
        JsonDateField dateAnnotation = mock(JsonDateField.class);
        LocalDateTime localDateTime = LocalDateTime.of(2025, 1, 23, 16, 42);
        FieldObject fieldObject = new FieldObject()
                .value(localDateTime)
                .elementName("dateTime")
                .annotation(List.of(jsonFieldAnnotation, dateAnnotation));

        //when
        when(jsonFieldAnnotation.name())
                .thenReturn("myDateTime");
        when(dateAnnotation.format())
                .thenReturn("YYYY-MM-DD HH:mm");
        String result = tested.serialize(fieldObject, localDateTime);
        //then
        assertEquals("\"myDateTime\":\"2025-01-23 16:42\"", result);
    }

}
