package com.blogspot.compilebreak.annotations.serializers.json;

import com.blogspot.compilebreak.annotations.mapping.JsonDocument;

@JsonDocument
class OtherTestObject {

    private String myString;

    public void setMyString(String myString) {
        this.myString = myString;
    }
}
