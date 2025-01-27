package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.Serializer;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTypedSerializerTest {

    @Test
    void shouldSerializeObject() {
        //given
        TestObject test = new TestObject();
        test.setProperty("someProperty");
        test.setSomething("test");

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"lol\":\"someProperty\",\"something\":\"test\",\"counter\":0}", result);
    }

    @Test
    void shouldSerializeOnlyNotNullElements() {
        //given
        TestObject test = new TestObject();
        test.setSomething("test");

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"something\":\"test\",\"counter\":0}", result);
    }

    @Test
    void shouldSerializeInt() {
        //given
        TestObject test = new TestObject();
        test.setSomething("test");
        test.setIntegerObject(25);
        test.setCounter(1);

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"something\":\"test\",\"integer\":25,\"counter\":1}", result);
    }

    @Test
    void shouldSerializeDate() {
        //given
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setDate(new Date(1737127169655L));

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"createDate\":\"2025/01/17\",\"counter\":0}", result);
    }

    @Test
    void shouldSerializeLocalDate() {
        //given
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setLocalDateTime(LocalDateTime.of(2025, 1, 23, 17, 27));

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"counter\":0,\"localDateTime\":\"2025-01-23\"}", result);
    }

    @Test
    void shouldSerializeLocalTime() {
        //given
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setLocalTime(LocalTime.of(17, 27));

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"counter\":0,\"localTime\":\"17:27\"}", result);
    }

    @Test
    void shouldSerializeArray() {
        //given
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setStringArray(new String[]{"one", "two", "three"});

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"counter\":0,\"stringArray\":[\"one\",\"two\",\"three\"]}", result);
    }

    @Test
    void shouldSerializeList() {
        //given
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setStringList(List.of("one", "two", "three"));

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"counter\":0,\"stringList\":[\"one\",\"two\",\"three\"]}", result);
    }

    @Test
    void shouldSubObject() {
        //given
        OtherTestObject otherTestObject = new OtherTestObject();
        otherTestObject.setMyString("secret");
        TestObject test = new TestObject();
        test.setCounter(0);
        test.setOtherTestObject(otherTestObject);

        //when
        String result = (String) new Serializer().serialize(test);

        //then
        assertEquals("{\"counter\":0,\"otherTestObject\":{\"myString\":\"secret\"}}", result);
    }
}
