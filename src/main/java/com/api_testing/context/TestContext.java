package com.api_testing.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private final Map<String, Object> contextData;

    public TestContext() {
        this.contextData = new HashMap<>();
    }

    public void set(String key, Object value) {
        contextData.put(key, value);
    }

    public Object get(String key) {
        return contextData.get(key);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(contextData.get(key));
    }

    public boolean containsKey(String key) {
        return contextData.containsKey(key);
    }

    public void remove(String key) {
        contextData.remove(key);
    }
}
