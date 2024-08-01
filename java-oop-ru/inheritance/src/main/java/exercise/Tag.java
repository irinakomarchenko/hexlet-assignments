package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String name;
    protected Map<String, String> attributes;

    public Tag(String name) {
        this.name = name;
        this.attributes = new LinkedHashMap<>();

    }
    public void setAttribute(String key, String value){
            attributes.put(key, value);
    }

    public String getAttribute(String key){
            return attributes.get(key);
    }

     public String getName() {
            return name;
    }

    protected Map<String, String> getAttributes() {
        return attributes;
    }

    protected String getOpeningTag() {
            String attrString = attributes.entrySet().stream()
                    .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(" "));
            return "<" + name + (attrString.isEmpty() ? "" : " " + attrString) + ">";
            }

    protected String getClosingTag() {
            return "</" + name + ">";
    }


    public String toHtml() {
            return getOpeningTag() + getClosingTag();
    }

    @Override
    public String toString() {
        return toHtml();
    }

}

// END
