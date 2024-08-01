package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
 public class SingleTag extends Tag {


    public SingleTag(String name, Map<String, String> attributes) {
        super(name);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            setAttribute(entry.getKey(), entry.getValue());
        }
    }


    @Override
    public String toHtml() {
        return getOpeningTag();
    }

    @Override
    public String toString() {
        return toHtml();
    }

}
// END
