// TestNGParameterStore.java
package com.orangehrm.listeners;

import java.util.HashMap;
import java.util.Map;

public class TestNGParameterStore {
    private static final Map<String, String> parameters = new HashMap<>();

    public static void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public static String getParameter(String key) {
        return parameters.get(key);
    }
}
