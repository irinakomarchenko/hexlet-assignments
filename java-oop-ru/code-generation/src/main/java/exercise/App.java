package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN

public class App {
    public static void save(Path filePath, Car car) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(car);
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения объекта в файл", e);
        }
    }

    public static Car extract(Path filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = Files.readString(filePath);
            return objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка извлечения объекта из файла", e);
        }
    }

// END
}