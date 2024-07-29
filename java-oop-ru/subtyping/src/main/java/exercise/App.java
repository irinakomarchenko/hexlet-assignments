package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String,String> data = storage.toMap();
        Map<String, String> swappeData = new HashMap<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            swappeData.put(entry.getValue(), entry.getKey());
        }

        for (String key : data.keySet()) {
            storage.unset(key);
        }

        for (Map.Entry<String, String> entry : swappeData.entrySet()){

            storage.set(entry.getKey(), entry.getValue());
        }
    }
}
// END
