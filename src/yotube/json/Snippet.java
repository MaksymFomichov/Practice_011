package yotube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public TopLevelComment topLevelComment;
}
