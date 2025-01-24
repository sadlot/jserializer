package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDateField;
import com.blogspot.compilebreak.annotations.mapping.JsonDocument;
import com.blogspot.compilebreak.annotations.mapping.JsonField;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author potatolot
 */
@JsonDocument
public class TestObject {

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }
}
