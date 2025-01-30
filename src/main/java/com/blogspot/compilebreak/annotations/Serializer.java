package com.blogspot.compilebreak.annotations;

import com.blogspot.compilebreak.annotations.exceptions.NotRecognizedDocumentException;
import com.blogspot.compilebreak.annotations.mapping.Document;
import com.blogspot.compilebreak.annotations.mapping.JsonDocument;
import com.blogspot.compilebreak.annotations.serializers.json.JsonTypedSerializer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Serializer {

    private static final Map<Class, TypedSerializer> TYPED_SERIALIZERS = Map.of(
            JsonDocument.class, new JsonTypedSerializer()
    );

    public Object serialize(Object object) {
        if (hasAnnotation(Document.class, object)) {
            throw new NotRecognizedDocumentException("Document not recognized. Given object class is missing corresponding annotation");
        }
        TypedSerializer typedSerializer = getCorrespondingSerializer(object);
        return typedSerializer.serialize(object);
    }

    private TypedSerializer getCorrespondingSerializer(Object object) {
        List<TypedSerializer> correspondingTypedSerializers = TYPED_SERIALIZERS.entrySet()
                .stream()
                .filter(serializer -> hasAnnotation(serializer.getKey(), object))
                .map(Entry::getValue)
                .toList();

        if (correspondingTypedSerializers.isEmpty()) {
            throw new NotRecognizedDocumentException("Given document type was not recognized.");
        }
        if (correspondingTypedSerializers.size() > 1) {
            throw new NotRecognizedDocumentException("Given document was specified as more than two types. Please specify only one type.");
        }
        return correspondingTypedSerializers.getFirst();
    }

    private boolean hasAnnotation(Class annotationClass, Object object) {
        return object.getClass().isAnnotationPresent(annotationClass);
    }
}
