package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.Serializer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
}
