package constants;

import io.qameta.allure.Allure;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>();

    public static void setEntry(String key, Object value) {
        if (context.get() == null) {
            context.set(new HashMap<>());
        }
        context.get().put(key.toLowerCase(), value);
    }

    public static Object getValue(String key) {
        return context.get().get(key.toLowerCase());
    }

}
