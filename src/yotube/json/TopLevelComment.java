package yotube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopLevelComment {
    public SnippetComment snippet;


}
