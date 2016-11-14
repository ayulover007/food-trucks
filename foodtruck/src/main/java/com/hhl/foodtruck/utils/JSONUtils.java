package com.hhl.foodtruck.utils;

import com.fasterxml.jackson.core.type.TypeReference;

public final class JSONUtils {

    private static JsonMapper defaultMapper = JsonMapper.getDefault();
    
    private JSONUtils() {

    }

    /**
     * Converts an object to its JSON representation.
     * 
     * @param o The object to convert.
     * @return The JSON string representation of the specified object.
     */
    public static String toJSON(final Object o) {
        return defaultMapper.toJSON(o);
    }

    /**
     * Instantiates an object using its JSON string representation.
     * 
     * @param json The JSON string representation of the class.
     * @param ref The type of object to instantiate.
     * @param <T> Generic used to provide the class type.
     * @return An object instantiated from the specified JSON.
     */
    public static <T> T fromJSON(final String json, @SuppressWarnings("rawtypes") final TypeReference ref) {
        return defaultMapper.fromJSON(json, ref);
    }
}
