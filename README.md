JSerializer is serializing java objects into:
- JSON
- XML (not implemented yet)
- Byte array (not implemented yet)

How to use:
1. Add `@JsonDocument` to your java class
2. Add `@JsonField` to your fields with `name` argument. This annotation is not required if you don't want to declare custom property name.
   Example:
   https://github.com/sadlot/jserializer/blob/main/src/test/java/com/blogspot/compilebreak/annotations/serializers/json/TestObject.java
3. Use Serializer class:

   ```
   new Serializer().serialize(test);
   ```
