package org.lulliloo.ouchallenge.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * helper class to convert between JSON to Java objects and back.
 *
 * @author <a href="mailto:tom@lulliloo.org">Tom McCann</a>
 */
public class JsonMarshall {
    public static <T> T toObject(String json, Class<T> classType) {
        if(json != null && !json.isEmpty()) {
            return GSON.fromJson(json, classType);
        } else {
            throw new RuntimeException("Invalid json passed, could not convert to " + classType);
        }
    }

    public static String fromObject(Object o) {
        if(o == null) {
            throw new RuntimeException("Can not convert null to JSON");
        } else {
            return GSON.toJson(o);
        }
    }

    private static final Gson GSON = new GsonBuilder().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
            setPrettyPrinting().
            create();
}
