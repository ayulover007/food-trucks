package com.hhl.foodtruck.utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Wrappers utility functions for the jackson JSON library.
 */
public class JsonMapper {

    private final ObjectMapper mapper;

    public JsonMapper() {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(Include.NON_EMPTY);
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    /**
     * Converts an object to its JSON representation.
     * 
     * @param o The object to convert.
     * @return The JSON string representation of the specified object.
     */
    public String toJSON(final Object o) {
        try {
            final StringWriter w = new StringWriter();
            this.mapper.writeValue(w, o);
            return w.toString();
        } catch (final IOException exn) {
            throw new IllegalStateException(exn);
        }
    }

    /**
     * Instantiates an object using its JSON string representation.
     * 
     * @param json The JSON string representation of the class.
     * @param ref The type of object to instantiate.
     * @param <T> Generic used to provide the class type.
     * @return An object instantiated from the specified JSON.
     */
    public <T> T fromJSON(final String json, @SuppressWarnings("rawtypes") final TypeReference ref) {
        try {
            return this.mapper.readValue(json, ref);
        } catch (final IOException exn) {
            throw new IllegalStateException(exn);
        }
    }

    public JsonMapper configure(final SerializationFeature f, final boolean state) {
        this.mapper.configure(f, state);
        return this;
    }

    public JsonMapper configure(final DeserializationFeature f, final boolean state) {
        this.mapper.configure(f, state);
        return this;
    }

    /**
     * Return the default mapper configuration.
     * 
     * @return {@link JsonMapper}.
     */
    public static JsonMapper getDefault() {
        final JsonMapper m = new JsonMapper()
                .configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return m;

    }

}
