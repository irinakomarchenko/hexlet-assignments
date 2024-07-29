package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testSetAndGet(){
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key","value"));
        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");
        assertThat(storage.get("key", "default")).isEqualTo("value");
    }


    @Test
    void testUnset(){
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key","value" , "key2",
                "value2"));
        storage.unset("key2");
        assertThat(storage.get("key2", "default")).isEqualTo("default");
        assertThat(storage.get("key", "default")).isEqualTo("value");
    }
    @Test
    void testGetWithDefault(){
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
       assertThat(storage.get("unknown", "default")).isEqualTo("default");
    }

    @Test
    void testToMap(){
        Map<String,String> initialData = Map.of("key", "value", "key2", "value2");
        KeyValueStorage storage = new FileKV(filepath.toString(),initialData);
        Map<String, String> data = storage.toMap();
        assertThat(data).isEqualTo(initialData);
    }

    @Test
    void testIntegration(){
        KeyValueStorage storage = new FileKV(filepath.toString(), new HashMap<>());

        storage.set("foo", "bar");
        storage.set("baz", "qux");
        assertThat(storage.get("foo", "default")).isEqualTo("bar");
        assertThat(storage.get("baz", "dafault")).isEqualTo("qux");

        storage.unset("foo");
        assertThat(storage.get("foo", "default")).isEqualTo("default");


        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("baz", "qux");
        assertThat(storage.toMap()).isEqualTo(expectedData);

    }
    // END
}
