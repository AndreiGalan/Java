package org.example.Compulsory;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private String id;
    private String name;
    private String path;
    private String url;
    private Map<String,String> tags;

    public Document() {}

    public Document(String id, String name) {
        this.id = id;
        this.name = name;
        this.tags = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addTag(String key, String value) {
        tags.put(key, value);
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", tags=" + tags +
                '}';
    }
}
