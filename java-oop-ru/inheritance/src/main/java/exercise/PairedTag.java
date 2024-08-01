package exercise;

import java.awt.*;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String content;
    private List<Tag> children;


    public PairedTag(String name, Map<String, String> attributes, String content, List<Tag> children) {
        super(name);
        this.content = content;
        this.children = children;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            setAttribute(entry.getKey(), entry.getValue());
        }

    }

    public List<Tag> getChildren() {
        return children;
    }


    @Override
    public String toHtml() {
        String openingTag = getOpeningTag();
        String closingTag = getClosingTag();

        String childrenHtml = children.stream()
                .map(Tag::toHtml)
                .collect(Collectors.joining());

        return openingTag + content + childrenHtml + closingTag;
    }

    @Override
    public String toString() {
        return toHtml();
    }

}
// END
