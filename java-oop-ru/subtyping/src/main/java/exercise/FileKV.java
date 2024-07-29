package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;


    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        Utils.writeFile(filePath, Utils.serialize(initialData));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = Utils.unserialize(Utils.readFile(filePath));
        data.put(key, value);
       Utils.writeFile(filePath, Utils.serialize(data));

    }

    @Override
    public void unset(String key) {
        Map<String, String> data = Utils.unserialize(Utils.readFile(filePath));
        data.remove(key);
        Utils.writeFile(filePath, Utils.serialize(data));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = Utils.unserialize(Utils.readFile(filePath));
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }

}
// END
