package com.blogspot.compilebreak.annotations.serializers.json.model;

import java.lang.annotation.Annotation;
import java.util.List;

public class FieldObject {

    private String elementName;
    private Class<?> elementType;
    private Object value;
    private List<Annotation> annotations;

    public String getElementName() {
        return elementName;
    }

    public Class<?> getElementType() {
        return elementType;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public Object getValue() {
        return value;
    }

    public FieldObject elementName(String elementName) {
        this.elementName = elementName;
        return this;
    }

    public FieldObject elementType(Class<?> elementType) {
        this.elementType = elementType;
        return this;
    }

    public FieldObject annotation(List<Annotation> annotations) {
        this.annotations = annotations;
        return this;
    }

    public FieldObject value(Object value) {
        this.value = value;
        return this;
    }

}
