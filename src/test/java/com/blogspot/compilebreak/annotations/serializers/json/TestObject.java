package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.mapping.JsonDocument;
import com.blogspot.compilebreak.annotations.mapping.JsonField;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * @author potatolot
 */
@JsonDocument
class TestObject {

    @JsonField(name = "lol")
    private String property;

    private String something;

    @JsonField(name = "integer")
    private Integer integerObject;

    @JsonField(name = "createDate")
    @JsonDateField(format = "YYYY/MM/DD")
    private Date date;

    private int counter;

    private LocalDateTime localDateTime;

    @JsonDateField(format = "HH:mm")
    private LocalTime localTime;

    private String[] stringArray;

    private List<String> stringList;

    private OtherTestObject otherTestObject;

    public void setProperty(String property) {
        this.property = property;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public void setIntegerObject(Integer integerObject) {
        this.integerObject = integerObject;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setOtherTestObject(OtherTestObject otherTestObject) {
        this.otherTestObject = otherTestObject;
    }
}
