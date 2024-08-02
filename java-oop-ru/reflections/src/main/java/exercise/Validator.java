package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> errors = new ArrayList<>();
        if (address.getCountry() == null) {
            errors.add("country");
        }
        if (address.getCity() == null) {
            errors.add("city");
        }

        if (address.getStreet() == null) {
            errors.add("street");
        }

        if (address.getHouseNumber() == null) {
            errors.add("houseNumber");
        }
        return errors;
    }
    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> errors = new HashMap<>();
        Field[] fields = Address.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(address);

                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add("must not be null");
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    int minLength = field.getAnnotation(MinLength.class).minLength();
                    if (value != null && value instanceof String && ((String) value).length() < minLength) {
                        errors.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add("length must be at least " + minLength);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } return errors.isEmpty() ? Map.of() : errors;
     }
}
// END
