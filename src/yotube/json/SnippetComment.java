package yotube.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetComment {
    public String textDisplay;
    public String authorDisplayName;
    public int likeCount;
    public String updatedAt;
    public String publishedAt;
}
