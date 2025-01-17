package com.blogspot.compilebreak.annotations.mapping;

/**
 *
 * @author potatolot
 */
public @interface JsonDateField {

    public String format() default "YYYY-MM-DD";

}
