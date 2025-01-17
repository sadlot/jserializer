package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDocument;
import com.blogspot.compilebreak.annotations.mapping.JsonField;

/**
 *
 * @author potatolot
 */
@JsonDocument
public class TestObject {

    @JsonField(name = "lol")
    private String property;

    private String something;

    @JsonField(name = "integer")
    private Integer integerObject;

    private int counter;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public Integer getIntegerObject() {
        return integerObject;
    }

    public void setIntegerObject(Integer integerObject) {
        this.integerObject = integerObject;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
